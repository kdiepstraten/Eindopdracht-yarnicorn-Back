package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
