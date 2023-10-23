package com.example.oefeningdataflow.Service;

import com.example.oefeningdataflow.DTO.ReservationDto;
import com.example.oefeningdataflow.Models.Reservation;
import com.example.oefeningdataflow.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservationDtoToReservation(reservationDto, reservation);

        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationDto savedReservationDto = new ReservationDto();
        reservationToReservationDto(savedReservation, savedReservationDto);

        return savedReservationDto;
    }

    private void reservationToReservationDto(Reservation reservation, ReservationDto rdto) {
        rdto.setFullName(reservation.getFullname());
        rdto.setEmail(reservation.getEmail());
        rdto.setStreet(reservation.getStreet());
        rdto.setStreetNumber(reservation.getStreetnumber());
        rdto.setZipcode(reservation.getZipcode());
        rdto.setCity(reservation.getCity());
        rdto.setAmount(reservation.getAmount());
        rdto.setComment(reservation.getComment());
    }
    private void reservationDtoToReservation(ReservationDto rdto, Reservation reservation){
        reservation.setFullname(rdto.getFullName());
        reservation.setEmail(rdto.getEmail());
        reservation.setStreet(rdto.getStreet());
        reservation.setStreetnumber(rdto.getStreetNumber());
        reservation.setZipcode(rdto.getZipcode());
        reservation.setCity(rdto.getCity());
        reservation.setAmount(rdto.getAmount());
        reservation.setComment(rdto.getComment());
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> res = reservationRepository.findAll();
        List<ReservationDto> resDtos = new ArrayList<>();

        for (Reservation r : res) {
            ReservationDto rDto = new ReservationDto();
            reservationToReservationDto(r, rDto);

            resDtos.add(rDto);
        }
        return resDtos;
    }
}
