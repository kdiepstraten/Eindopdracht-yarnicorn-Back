package com.example.oefeningdataflow.Controllers;

import com.example.oefeningdataflow.DTO.ProfileDto;
import com.example.oefeningdataflow.DTO.ReservationDto;
import com.example.oefeningdataflow.Service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reservation")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto rdto = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(rdto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> rDto = reservationService.getAllReservations();
        return new ResponseEntity<>(rDto, HttpStatus.OK);
    }
}
