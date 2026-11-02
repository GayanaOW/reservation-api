package com.example.reservation_api.event;

public record SeatReservedEvent(
    String seatNumber,
    String customerEmail
) {}