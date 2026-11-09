package com.example.reservation_api.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.reservation_api.event.SeatReservedEvent;

@Component
public class ReservationEventListener {

    @KafkaListener(topics = "seat-reservations", groupId = "reservation-group")
    public void handleSeatReserved(SeatReservedEvent event) {
        System.out.println("----------------------------------------");
        System.out.println(" [Kafka Consumer] Received Reservation Event!");
        System.out.println(" Seat Number  : " + event.seatNumber());
        System.out.println(" Customer     : " + event.customerEmail());
        System.out.println("----------------------------------------");
        
        // Here is where you could trigger an email, update analytics, etc.
    }
}