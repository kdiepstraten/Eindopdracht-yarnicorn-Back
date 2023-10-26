package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProductDto;
import com.example.eindopdrachtyarnicornback.DTO.UserDto;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Models.User;
import com.example.eindopdrachtyarnicornback.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;
    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setUsername("Taylor");
        user1.setPassword("HelloimTaylor");

        User user2 = new User();
        user2.setUsername("Tom");
        user2.setPassword("imamspiderman");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDto> udto = userService.getAllUsers();

        assertEquals(2, udto.size());
    }

//    @Test
//    void createUser() {
//
//        UserDto userDto = new UserDto();
//        userDto.setUsername("Jake");
//        userDto.setPassword("hellofromthemountain");
//
//        User user = new User();
//        user.setUsername(userDto.getUsername());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//
//        UserDto userDTO = userService.createUser(userDto);
//
//        assertEquals("Jake", userDTO.getUsername());
//        assertEquals("hellofromthemountain", userDTO.getPassword());
//
//    }
}