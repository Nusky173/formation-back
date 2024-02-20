package group.fortil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsService userService;

//    @PostMapping("/login")
//    public void login(@RequestBody LoginRequest loginRequest) {
//        Authentication authenticationReuest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest)
//    }
}
