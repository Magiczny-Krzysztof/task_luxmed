package com.task.controller;

import com.task.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.service.UserService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
@Validated
public class UserController {

    private final UserService userService;
    private final Object obj = new Object();

    @GetMapping(value="/{login}")
    public UserDto getUserData(@PathVariable("login") @NotBlank @NotNull @Size(max = 39) String login) {
        login = login.toUpperCase();
        if (userService.saveUserCallOccurenceIfExists(login).isEmpty()) {
            synchronized (obj) {
                userService.insertNewCall(login);
            }
        }

        return userService.getUserData(login);
    }

}

