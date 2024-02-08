package group.fortil.controller;

import group.fortil.dto.MessageDtoImpl;
import group.fortil.dto.TagDtoImpl;
import group.fortil.dto.UserDtoImpl;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.service.MessageServiceImpl;
import group.fortil.service.TagServiceImpl;
import group.fortil.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/message")
@Validated
public class MessageControllerImpl implements IMessageController {

    @Autowired
    MessageServiceImpl<MessageDtoImpl> messageService;

    @Autowired
    UserServiceImpl<UserDtoImpl> userService;

    @Autowired
    TagServiceImpl<TagDtoImpl> tagService;

    /**
     * @param date accept filtering on publicationDate for a message
     *             /api/message?date="year,month,day"
     * @return List of message depend on the param
     */
    @Override
    @GetMapping
    public List<MessageDtoImpl> getMessage(@RequestParam(value = "date", required = false) String date) throws EntityNotFoundException {
        List<MessageDtoImpl> response = messageService.findAll();

        //If we want to filter the response on message.publicationDate
        if (date != null) {
            String[] filters = date.split(",");


            response = response.stream().filter(messageDto -> {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(messageDto.getPublishDate());

                //If only year is specified on @param date filter
                if (filters.length == 1) {

                    return (Integer.parseInt(filters[0]) == calendar1.get(Calendar.YEAR));
                }
                //If both year and month are specified on @param date filter
                else if (filters.length == 2) {
                    return (Integer.parseInt(filters[0]) == calendar1.get(Calendar.YEAR))
                        && Integer.parseInt(filters[1]) == calendar1.get(Calendar.MONTH);
                }
                // if third year, month and day are specified on @param date filter
                else {
                    return (Integer.parseInt(filters[0]) == calendar1.get(Calendar.YEAR))
                        && Integer.parseInt(filters[1]) == calendar1.get(Calendar.MONTH)
                        && Integer.parseInt(filters[2]) == calendar1.get(Calendar.DAY_OF_MONTH);
                }
            }).collect(Collectors.toList());
        }
        return response;
    }

    @Override
    @GetMapping(value = "/{messageId}")
    public MessageDtoImpl getMessage(@PathVariable("messageId") Long messageId) throws EntityNotFoundException {
        return messageService.findById(messageId);
    }

    @Override
    @PostMapping
    public MessageDtoImpl createMessage(@RequestBody MessageDtoImpl message) {
        return messageService.create(message);
    }

    @Override
    @PutMapping
    public MessageDtoImpl updateMessage(@RequestBody @Valid MessageDtoImpl message) throws EntityNotFoundException {
        return messageService.update(message);
    }

    @Override
    @PutMapping(value = "/{messageId}/tag/{tagId}")
    public MessageDtoImpl deleteTagForMessage(
        @PathVariable("messageId") Long messageId,
        @PathVariable("tagId") Long tagId
    ) throws EntityNotFoundException {
        MessageDtoImpl messageDto = messageService.findById(messageId);

        TagDtoImpl tagToAdd = tagService.findById(tagId);

        messageDto.setTagList(
            messageDto
                .getTagList()
                .stream()
                .filter(e -> e.getIndex() != tagId)
                .toList());

        return messageService.update(messageDto);
    }

    @Override
    @DeleteMapping(value = "/{messageId}")
    public void deleteMessage(@PathVariable("messageId") Long messageId) throws EntityNotFoundException {
        messageService.delete(messageId);
    }

    @Override
    @GetMapping("/{messageId}/tag")
    public List<TagDtoImpl> getMessageTag(@PathVariable("messageId") Long messageId) throws EntityNotFoundException {
        MessageDtoImpl messageDto = messageService.findById(messageId);

        return new ArrayList<>(messageDto.getTagList());
    }

    @Override
    @GetMapping(value = "/owner/{ownerId}")
    public List<MessageDtoImpl> getUserMessage(@PathVariable("ownerId") Long ownerId) throws EntityNotFoundException {
        UserDtoImpl owner = userService.findById(ownerId);

        return messageService.findMessagesByUserIndex(owner);
    }


    @Override
    @PostMapping("/{messageId}/tag")
    public MessageDtoImpl postTagOnMessage(
        @PathVariable("messageId") Long messageId,
        @RequestBody TagDtoImpl tagDto
    ) throws EntityNotFoundException {
        MessageDtoImpl messageToUpdate = messageService.findById(messageId);

        TagDtoImpl tagToAdd = tagService.findById(tagDto.getIndex());

        messageToUpdate.getTagList().add(tagToAdd);

        return messageService.update(messageToUpdate);
    }
}
