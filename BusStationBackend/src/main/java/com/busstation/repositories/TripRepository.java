package com.busstation.repositories;

import com.busstation.pojo.Seat;

import java.util.List;

public interface TripRepository {
    List<Seat> getAvailableSeats(Long id);
    List<Seat> getUnAvailableSeats(Long id);
}
