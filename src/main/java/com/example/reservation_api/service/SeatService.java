package com.example.reservation_api.service;

import com.example.reservation_api.event.SeatReservedEvent;
import com.example.reservation_api.model.Seat;
import com.example.reservation_api.repository.SeatRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final KafkaTemplate<String, SeatReservedEvent> kafkaTemplate;

    public SeatService(SeatRepository seatRepository, KafkaTemplate<String, SeatReservedEvent> kafkaTemplate) {
        this.seatRepository = seatRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
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
        Seat updatedSeat = seatRepository.save(seat);

        // Emit event to Kafka topic
        SeatReservedEvent event = new SeatReservedEvent(seatNumber, customerEmail);
        kafkaTemplate.send("seat-reservations", seatNumber, event);

        return updatedSeat;
    }
}