package com.example.eindopdrachtyarnicornback.Controllers;

import com.example.eindopdrachtyarnicornback.DTO.ProfileAndUserDto;
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

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable String username) {
        UserDto dDto = userService.getUser(username);
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUserWithProfile(@Valid @RequestBody ProfileAndUserDto userDto) {
        UserDto result = userService.createUserWithProfile(userDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @Valid @RequestBody UserDto userDto) {
        UserDto result = userService.updateUser(username, userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



