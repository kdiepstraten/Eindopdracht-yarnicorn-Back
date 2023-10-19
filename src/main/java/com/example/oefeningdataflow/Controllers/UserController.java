package com.example.oefeningdataflow.Controllers;
import com.example.oefeningdataflow.DTO.UserDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.oefeningdataflow.Models.Role;
import com.example.oefeningdataflow.Models.User;
import com.example.oefeningdataflow.Repository.RoleRepository;
import com.example.oefeningdataflow.Repository.UserRepository;
import com.example.oefeningdataflow.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class UserController {

    private final UserService userService;

    private final RoleRepository roleRepos;
    private final UserRepository userRepos;
    private final PasswordEncoder encoder;

    public UserController(UserService userService, RoleRepository roleRepos, UserRepository userRepos, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleRepos = roleRepos;
        this.userRepos = userRepos;
        this.encoder = encoder;
    }

    @GetMapping

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        String newUserName = userService.createNewUser(userDto);
        //adding authority to user: //
        userService.addAuthority(newUserName, "ROLE_USER");

        // -- ADDING location to user via URI --- //

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/username")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();

    }


}
