package com.example.oefeningdataflow.Service;
import com.example.oefeningdataflow.DTO.UserDto;
import com.example.oefeningdataflow.Exceptions.UserNotFoundException;
import com.example.oefeningdataflow.Models.Role;
import com.example.oefeningdataflow.Models.User;
import com.example.oefeningdataflow.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) {
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);

            userDtos.add(uDto);
        }
        return userDtos;
    }

    private static void userToUserDto(User u, UserDto uDto) {
        uDto.setUsername(u.getUsername());
        uDto.setPassword(u.getPassword());
    }
    private static void userDtoToUser(User u, UserDto uDto) {
        u.setUsername(uDto.getUsername());
        u.setPassword(uDto.getPassword());
    }
    public String createNewUser(UserDto userDto) {
        // Convert UserDto to User entity (you may need additional properties)
        User newUser = new User();

      userToUserDto(newUser, userDto);
        // Set other user properties...

        // Securely hash the user's password (assuming a field named 'password' in User entity)
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        newUser.setPassword(hashedPassword);

        // Save the new user to the repository
        userRepository.save(newUser);

        return newUser.getUsername(); // Return the username of the newly created user
    }
    public void addAuthority(String username, String newRole) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            // Create a new authority (e.g., a role) and add it to the user
            Role role = new Role();
            role.setRoleName(newRole);

            user.getRoles().add(role);
            // Update the user in the repository
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with username: " + username);
        }
    }
}

