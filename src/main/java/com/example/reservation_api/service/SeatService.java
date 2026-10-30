package com.example.reservation_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation_api.model.Seat;
import com.example.reservation_api.repository.SeatRepository;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Transactional
    public Seat reserveSeat(String seatNumber, String customerEmail) {
        Seat seat = seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found: " + seatNumber));

        if (seat.isReserved()) {
            throw new IllegalStateException("Seat " + seatNumber + " is already reserved.");
        }

        seat.setReserved(true);
        seat.setReservedBy(customerEmail);
        return seatRepository.save(seat);
    }
}