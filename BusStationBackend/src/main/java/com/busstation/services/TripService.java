package com.busstation.services;

import com.busstation.pojo.Seat;

import java.util.Map;

public interface TripService {
    Map<String, Object> seatDetails(Long id);

}
