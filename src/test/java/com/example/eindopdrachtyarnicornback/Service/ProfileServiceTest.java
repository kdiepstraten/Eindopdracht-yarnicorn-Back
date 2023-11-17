package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProfileDto;
import com.example.eindopdrachtyarnicornback.Models.Profile;
import com.example.eindopdrachtyarnicornback.Repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @Mock
    ProfileRepository profileRepository;
    @InjectMocks
    ProfileService profileService;

    @Test
    void getAllProfile() {
        Profile profile1 = new Profile();

        profile1.setFirstName("Taylor");
        profile1.setLastName("Zakhar Perez");
        profile1.setEmail("tperez@gmail.com");

        Profile profile2 = new Profile();

        profile1.setFirstName("Tom");
        profile1.setLastName("Holland");
        profile1.setEmail("tommieboy@gmail.com");

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        Mockito.when(profileRepository.findAll()).thenReturn(profiles);

        List<ProfileDto> pdto = profileService.getAllProfile();

        assertEquals(2, pdto.size());
    }

    @Test
    void createProfile() {
    }

    @Test
    void deleteProfile() {
        Long productId = 1L;
        Mockito.doNothing().when(profileRepository).deleteById(productId);

        String result = profileService.deleteProfile(productId);

        assertEquals("Profile deleted", result);
        Mockito.verify(profileRepository, Mockito.times(1)).deleteById(productId);

    }
}