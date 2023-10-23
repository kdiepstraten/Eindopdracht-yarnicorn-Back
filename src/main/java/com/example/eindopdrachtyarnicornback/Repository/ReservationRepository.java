package com.example.eindopdrachtyarnicornback.Repository;

import com.example.eindopdrachtyarnicornback.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
