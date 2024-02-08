package group.fortil.controller;

import group.fortil.dto.TagDtoImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag")
public interface ITagController extends IBaseController {

    @GetMapping
    List<TagDtoImpl> getTags(@RequestParam(name = "name", required = false) String name) throws Exception;

    @GetMapping(value = "/{tagId}")
    TagDtoImpl getTag(@PathVariable("tagId") Long tagId) throws Exception;

    @PostMapping
    TagDtoImpl createTag(@Valid @RequestBody TagDtoImpl tag) throws Exception;

    @PutMapping
    TagDtoImpl updateTag(@Valid @RequestBody TagDtoImpl tag) throws Exception;

    @DeleteMapping("/{tagId}")
    void deleteTag(@PathVariable("tagId") Long tagId) throws Exception;

}
