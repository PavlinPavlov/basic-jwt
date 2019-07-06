package com.jwt.controllers;

import com.jwt.pojo.JwtUser;
import com.jwt.services.JwtService;
import com.jwt.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    private final UserService userService;

    private final JwtService jwtService;

    public JwtController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping(value = "/secure/hello/{name}")
    public ResponseEntity<String> helloSecure(@PathVariable String name) {
        String result = String.format("Hello JWT, %s! (Secure)", name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/public/auth")
    public ResponseEntity<String> auth(@RequestBody JwtUser jwtUser) {
        String userName = jwtUser.getUsername();
        String passWord = jwtUser.getPassword();

        Boolean correctCredentials = userService.authenticate(userName, passWord);

        if (correctCredentials)
            return new ResponseEntity<>(jwtService.getToken(jwtUser), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
