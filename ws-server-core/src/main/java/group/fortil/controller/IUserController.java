package group.fortil.controller;

import group.fortil.dto.UserDtoImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Validated
public interface IUserController extends IBaseController {

    @GetMapping
    List<UserDtoImpl> getUser(@RequestParam(name = "email", required = false) @Email String email) throws Exception;

    @GetMapping(value = "/{userId}")
    UserDtoImpl getUser(@PathVariable("userId") Long userId) throws Exception;

    @PostMapping
    UserDtoImpl createUser(@RequestBody @Valid UserDtoImpl user) throws Exception;

    @PutMapping
    UserDtoImpl updateUser(@RequestBody @Valid UserDtoImpl user) throws Exception;

    @DeleteMapping(value = "/{userId}")
    void deleteUser(@PathVariable("userId") Long userId) throws Exception;

}
