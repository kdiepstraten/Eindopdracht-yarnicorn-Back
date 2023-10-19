package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
