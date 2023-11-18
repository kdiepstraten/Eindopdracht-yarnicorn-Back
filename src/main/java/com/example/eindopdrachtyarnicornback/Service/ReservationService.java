package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ReservationDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Exceptions.InvalidAmountException;
import com.example.eindopdrachtyarnicornback.Exceptions.ProductIdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Models.Reservation;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import com.example.eindopdrachtyarnicornback.Repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProductRepository productRepository;

    public ReservationService(ReservationRepository reservationRepository, ProductRepository productRepository) {
        this.reservationRepository = reservationRepository;
        this.productRepository = productRepository;
    }


    public ReservationDto createReservation(ReservationDto reservationDto, Long productId) {

        if (reservationDto.getAmount() < 0) {
            throw new InvalidAmountException("Invalid reservation amount: " + reservationDto.getAmount());
        }

        Reservation reservation = new Reservation();
        reservationDtoToReservation(reservationDto, reservation);


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductIdNotFoundException("Product not found with id: " + productId));

        reservation.setProduct(product);
        reservationDto.setProductId(product.getId());


        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationDto savedReservationDto = new ReservationDto();
        reservationToReservationDto(savedReservation, savedReservationDto);
        savedReservationDto.setProductId(product.getId());
        return savedReservationDto;

    }

    private void reservationToReservationDto(Reservation reservation, ReservationDto rdto) {
        rdto.setFullName(reservation.getFullName());
        rdto.setEmail(reservation.getEmail());
        rdto.setStreet(reservation.getStreet());
        rdto.setStreetNumber(reservation.getStreetNumber());
        rdto.setZipcode(reservation.getZipcode());
        rdto.setCity(reservation.getCity());
        rdto.setAmount(reservation.getAmount());
        rdto.setComment(reservation.getComment());
        rdto.setProductId(reservation.getProduct().getId());
        rdto.setId(reservation.getId());
    }

    private void reservationDtoToReservation(ReservationDto rdto, Reservation reservation) {
        reservation.setFullName(rdto.getFullName());
        reservation.setEmail(rdto.getEmail());
        reservation.setStreet(rdto.getStreet());
        reservation.setStreetNumber(rdto.getStreetNumber());
        reservation.setZipcode(rdto.getZipcode());
        reservation.setCity(rdto.getCity());
        reservation.setAmount(rdto.getAmount());
        reservation.setComment(rdto.getComment());
        reservation.setId(rdto.getId());
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

    public String deleteReservation(@RequestBody Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new IdNotFoundException("Profile not found with ID: " + id);
        }

        return "Profile deleted";
    }
}
