package com.busstation.services;

import com.busstation.dtos.RouteDTO;
import com.busstation.dtos.TripDTO;
import com.busstation.pojo.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {
    Map<String, Object> list(Map<String, String> params);
    RouteDTO getById(Long id);

    List<TripDTO> getTripList(Long id);
}
