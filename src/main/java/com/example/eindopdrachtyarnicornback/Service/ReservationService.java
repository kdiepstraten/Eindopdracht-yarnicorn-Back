package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ReservationDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Models.Reservation;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import com.example.eindopdrachtyarnicornback.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

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
        Reservation reservation = new Reservation();
        reservationDtoToReservation(reservationDto, reservation);

        // Retrieve the associated product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException("Product not found with id: " + productId));

        reservation.setProduct(product); // Associate the reservation with the product
        reservationDto.setProductId(product.getId()); // Adds the ProductId to the DTO


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
