package group.fortil.controller;

import group.fortil.dto.MessageDtoImpl;
import group.fortil.dto.TagDtoImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public interface IMessageController extends IBaseController {

    /**
     * @param date accept filtering on publicationDate for a message
     *             /api/message?date="year,month,day"
     * @return List of message depend on the param
     */
    @GetMapping
    List<MessageDtoImpl> getMessage(@RequestParam(value = "date", required = false) String date) throws Exception;

    @GetMapping(value = "/{messageId}")
    MessageDtoImpl getMessage(@PathVariable("messageId") Long messageId) throws Exception;

    @PostMapping
    MessageDtoImpl createMessage(@RequestBody @Valid MessageDtoImpl message) throws Exception;

    @PutMapping
    MessageDtoImpl updateMessage(@RequestBody @Valid MessageDtoImpl message) throws Exception;

    @PutMapping(value = "/{messageId}/tag/{tagId}")
    MessageDtoImpl deleteTagForMessage(
        @PathVariable("messageId") Long messageId,
        @PathVariable("tagId") Long tagId
    ) throws Exception;

    @DeleteMapping(value = "/{messageId}")
    void deleteMessage(@PathVariable("messageId") Long messageId) throws Exception;

    @GetMapping(value = "/{messageId}/tag")
    List<TagDtoImpl> getMessageTag(@PathVariable("messageId") Long messageId) throws Exception;

    /**
     * @param userId of user to retrieve all message posted
     * @return
     */
    @GetMapping(value = "/{ownerId}")
    List<MessageDtoImpl> getUserMessage(@PathVariable("ownerId") Long userId) throws Exception;

    /**
     * @param messageId of message to edit
     * @param tag       post
     * @return message dto object
     */
    @PostMapping(value = "/{messageId}/tag")
    MessageDtoImpl postTagOnMessage(
        @PathVariable("messageId") Long messageId,
        @RequestBody @Valid TagDtoImpl tag
    ) throws Exception;
}
