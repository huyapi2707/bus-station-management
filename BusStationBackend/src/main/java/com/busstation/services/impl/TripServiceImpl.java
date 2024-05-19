package com.busstation.services.impl;

import com.busstation.pojo.Seat;
import com.busstation.repositories.TripRepository;
import com.busstation.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Map<String, Object> seatDetails(Long id) {
        List<Seat> availableSeat = tripRepository.getAvailableSeats(id);
        List<Seat> unAvailableSeat = tripRepository.getUnAvailableSeats(id);
        Map<String, Object> result = new HashMap<>();
        result.put("availableSeat", availableSeat);
        result.put("unAvailableSeat", unAvailableSeat);
        return result;
    }
}
