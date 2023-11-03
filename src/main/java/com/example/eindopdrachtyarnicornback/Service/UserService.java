package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProfileAndUserDto;
import com.example.eindopdrachtyarnicornback.DTO.ProfileDto;
import com.example.eindopdrachtyarnicornback.DTO.UserDto;
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


    public UserDto createUserWithProfile(ProfileAndUserDto profileDto) {

        // User gedeelte van de ProfileDTO
        UserDto userDto = new UserDto();
        userDto.setUsername(profileDto.getUsername());
        userDto.setPassword(passwordEncoder.encode(profileDto.getPassword()));

        User user = new User();
    if (profileDto.getRoles() != null) {
        List<Role> userRoles = new ArrayList<>();
        for (String rolename : profileDto.getRoles()) {
            Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }

        // Aanmaken User
        userDtoToUser(user, userDto);
        user.setRoles(userRoles);
    }
        // Aanmaken Profile
        Profile profile = new Profile();
        profileDtoToProfile(profileDto, profile);

        // OneToOne relatie tussen User en Profile
        profile.setUser(user);

        // Beide opslaan in Repository
        userRepository.save(user);
        profileRepository.save(profile);

        // User -> UserDTO om terug te geven naar de controller
        UserDto savedUserDto = new UserDto();
        userToUserDto(user, savedUserDto);

        return savedUserDto;
    }


    private void profileDtoToProfile(ProfileAndUserDto pDto, Profile p) {
//        p.setUsername(pDto.getUsername());
//        p.setPassword(pDto.getPassword());
//        p.setConfirmPassword(pDto.getConfirmPassword());
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }
}

