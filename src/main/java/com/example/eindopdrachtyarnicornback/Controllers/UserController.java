package com.example.eindopdrachtyarnicornback.Controllers;

import com.example.eindopdrachtyarnicornback.DTO.UserDto;

import java.util.List;

import com.example.eindopdrachtyarnicornback.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto result = userService.createUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}



