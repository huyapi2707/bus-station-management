package com.busstation.services;

import com.busstation.dtos.TripDTO;
import com.busstation.pojo.Seat;

import java.util.Map;

public interface TripService {
    Map<String, Object> seatDetails(Long id);
    TripDTO tripInfo(Long id);
}
