package group.fortil.controller;

import group.fortil.dto.TagDtoImpl;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.service.TagServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag")
@Validated
public class TagControllerImpl implements ITagController {

    @Autowired
    TagServiceImpl<TagDtoImpl> tagService;

    @Override
    @GetMapping
    public List<TagDtoImpl> getTags(@RequestParam(name = "name", required = false) String name) throws EntityNotFoundException {
        List<TagDtoImpl> result = tagService.findAll();

        if (name == null) {
            return result;
        }

        if (name.isBlank()) {
            return result;
        }

        return result
            .stream()
            .filter((e) -> e.getValue().equals(name) ||
                e.getValue().startsWith(name))
            .toList();
    }

    @Override
    @GetMapping(value = "/{tagId}")
    public TagDtoImpl getTag(@PathVariable("tagId") Long tagId) throws EntityNotFoundException {
        return tagService.findById(tagId);
    }

    @Override
    @PostMapping
    public TagDtoImpl createTag(@Valid @RequestBody TagDtoImpl tag) {
        return tagService.create(tag);
    }

    @Override
    @PutMapping
    public TagDtoImpl updateTag(@Valid @RequestBody TagDtoImpl tag) throws EntityNotFoundException {
        return tagService.update(tag);
    }

    @Override
    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable("tagId") Long tagId) throws EntityNotFoundException {
        tagService.delete(tagId);
    }
}
