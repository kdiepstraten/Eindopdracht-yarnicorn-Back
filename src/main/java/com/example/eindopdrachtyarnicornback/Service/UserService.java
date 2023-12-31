package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProfileAndUserDto;
import com.example.eindopdrachtyarnicornback.DTO.UserDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.Profile;
import com.example.eindopdrachtyarnicornback.Models.Role;
import com.example.eindopdrachtyarnicornback.Models.User;
import com.example.eindopdrachtyarnicornback.Repository.ProfileRepository;
import com.example.eindopdrachtyarnicornback.Repository.RoleRepository;
import com.example.eindopdrachtyarnicornback.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ProfileRepository profileRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
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
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : u.getRoles()) {
            roles.add(role.getRoleName());
        }
        uDto.setRoles(roles.toArray(new String[0]));
    }

    private static void userDtoToUser(User u, UserDto uDto) {
        u.setUsername(uDto.getUsername());
        u.setPassword(uDto.getPassword());
    }


    public UserDto createUserWithProfile(ProfileAndUserDto userDto) {

        UserDto userDto1 = new UserDto();
        userDto1.setUsername(userDto.getUsername());
        userDto1.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto1.setRoles(userDto.getRoles());

        User user = new User();
        userDtoToUser(user, userDto1);

    if (userDto1.getRoles() != null) {
        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto1.getRoles()) {
            Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }
        user.setRoles(userRoles);
    }

        Profile profile = new Profile();
        profileDtoToProfile(userDto, profile);


        profile.setUser(user);
        user.setProfile(profile);

        userRepository.save(user);
        profileRepository.save(profile);


        UserDto savedUserDto = new UserDto();
        userToUserDto(user, savedUserDto);

        return savedUserDto;
    }

    public UserDto getUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            User u = user.get();
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);
            return (uDto);
        } else {
            throw new IdNotFoundException("User not found with username: " + username);
        }
    }
    public UserDto updateUser(String username, UserDto userDto) {
        if (userRepository.existsById(username)) {
            UserDto uDto = new UserDto();
            uDto.setUsername(username);
            uDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            uDto.setRoles(userDto.getRoles());

            User user = userRepository.findById(username).get();
            userDtoToUser(user, uDto);
            User savedUser = userRepository.save(user);
            UserDto savedUserDto = new UserDto();
            userToUserDto(savedUser, savedUserDto);
            return savedUserDto;
        } else {
            throw new IdNotFoundException("User not found with username: " + username);
        }
    }
    private void profileDtoToProfile(ProfileAndUserDto pDto, Profile p) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }

    public String deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        } else {
            throw new IdNotFoundException("User not found with username: " + username);
        }
        return "User deleted";

    }

}


