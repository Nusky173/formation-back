package group.fortil.controller;

import group.fortil.dto.UserDtoImpl;
import group.fortil.exception.EntityNotFoundException;
import group.fortil.service.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserControllerImpl implements IUserController {

    @Autowired
    UserServiceImpl<UserDtoImpl> userService;

    @Override
    @GetMapping
    public List<UserDtoImpl> getUser(@RequestParam(name = "email", required = false) @Email String email) throws EntityNotFoundException {
        List<UserDtoImpl> response = userService.findAll();

        if (email != null && !email.isBlank()) {
            response = response.stream().filter(filtered ->
                email.equals(filtered.getEmailAddress())
            ).toList();

            if (response.isEmpty()) {
                throw new EntityNotFoundException(String.format("Not user found for email : %s", email));
            }
            return response;
        }

        return response;
    }

    @Override
    @GetMapping(value = "/{userId}")
    public UserDtoImpl getUser(@PathVariable("userId") Long userId) throws EntityNotFoundException {
        return userService.findById(userId);
    }

    @Override
    @PostMapping
    public UserDtoImpl createUser(@RequestBody @Valid UserDtoImpl user) {
        return userService.create(user);
    }

    @Override
    @PutMapping
    public UserDtoImpl updateUser(@RequestBody @Valid UserDtoImpl user) throws EntityNotFoundException {

        return userService.update(user);
    }

    @Override
    @DeleteMapping(value = "/{ownerId}")
    public void deleteUser(@PathVariable("ownerId") Long userId) throws EntityNotFoundException {
        userService.delete(userId);
    }
}
