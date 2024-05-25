package com.busstation.services.impl;

import com.busstation.dtos.TripDTO;
import com.busstation.mappers.TripDTOMapper;
import com.busstation.pojo.Seat;
import com.busstation.pojo.Trip;
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

    @Autowired
    private TripDTOMapper tripDTOMapper;

    @Override
    public Map<String, Object> seatDetails(Long id) {
        List<Seat> availableSeat = tripRepository.getAvailableSeats(id);
        List<Seat> unAvailableSeat = tripRepository.getUnAvailableSeats(id);
        Map<String, Object> result = new HashMap<>();
        result.put("availableSeat", availableSeat);
        result.put("unAvailableSeat", unAvailableSeat);
        return result;
    }

    @Override
    public TripDTO tripInfo(Long id) {
        Trip trip = tripRepository.getById(id);
        return tripDTOMapper.apply(trip);
    }

    @Override
    public Trip getById(Long id) {
        return tripRepository.getById(id);
    }

    @Override
    public Seat availableSeat(Long tripId, Long seatId) {
        return tripRepository.availableSeat(tripId, seatId);
    }
}
