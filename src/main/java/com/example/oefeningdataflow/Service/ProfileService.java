package com.example.oefeningdataflow.Service;

import com.example.oefeningdataflow.DTO.ProfileDto;
import com.example.oefeningdataflow.DTO.UserDto;
import com.example.oefeningdataflow.Exceptions.IdNotFoundException;
import com.example.oefeningdataflow.Models.Profile;
import com.example.oefeningdataflow.Models.User;
import com.example.oefeningdataflow.Repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public List<ProfileDto> getAllProfile() {
        List<Profile> profile = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();

        for (Profile p : profile) {
            ProfileDto pDto = new ProfileDto();
            profileToProfileDto(p, pDto);

            profileDtos.add(pDto);
        }
        return profileDtos;
    }

    private static void profileToProfileDto(Profile p, ProfileDto pDto) {
        pDto.setUsername(p.getUsername());
        pDto.setPassword(p.getPassword());
        pDto.setConfirmPassword(p.getConfirmPassword());
        pDto.setFirstName(p.getFirstName());
        pDto.setLastName(p.getLastName());
        pDto.setEmail(p.getEmail());
    }

    private void profileDtoToProfile(ProfileDto pDto, Profile p) {
        p.setUsername(pDto.getUsername());
        p.setPassword(pDto.getPassword());
        p.setConfirmPassword(pDto.getConfirmPassword());
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }

    public ProfileDto getProfile(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            Profile p = profile.get();
            ProfileDto pDto = new ProfileDto();
            profileToProfileDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public ProfileDto createProfile(ProfileDto profileDto) {
        Profile profile = new Profile();
        profileDtoToProfile(profileDto, profile);

        Profile savedProfile = profileRepository.save(profile);

        ProfileDto savedProfileDto = new ProfileDto();
        profileToProfileDto(savedProfile, savedProfileDto);

        return savedProfileDto;
    }

    public void deleteProfile(@RequestBody Long id) {
        profileRepository.deleteById(id);
    }
}
