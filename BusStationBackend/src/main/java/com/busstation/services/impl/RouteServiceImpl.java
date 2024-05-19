package com.busstation.services.impl;

import com.busstation.dtos.RouteDTO;
import com.busstation.dtos.TripDTO;
import com.busstation.mappers.RouteDTOMapper;
import com.busstation.mappers.TripDTOMapper;
import com.busstation.pojo.Route;
import com.busstation.pojo.Trip;
import com.busstation.repositories.RouteRepository;
import com.busstation.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:configuration.properties")
public class RouteServiceImpl implements RouteService {

    @Autowired
    private Environment environment;

    @Autowired
    private RouteRepository repository;

    @Autowired
    private RouteDTOMapper routeDTOMapper;

    @Autowired
    private TripDTOMapper tripDTOMapper;
    @Override
    public Map<String, Object> list(Map<String, String> params) {
        List<Route> results = repository.list(params);
        Long total = repository.count(params);
        int pageSize = Integer.parseInt(environment.getProperty("route.pageSize"));
        int pageTotal = (int) Math.ceil(total / pageSize) ;
        Map<String, Object> m = new HashMap<>();
        m.put("results", results.stream().map(routeDTOMapper::apply).collect(Collectors.toList()));
        m.put("total", total);
        m.put("pageTotal", pageTotal);
        return m;
    }

    @Override
    public RouteDTO getById(Long id) {
        Route route = repository.getById(id);
        return routeDTOMapper.apply(route);
    }

    @Override
    public List<TripDTO> getTripList(Long id) {
        Route route = repository.getById(id);
        if (route == null)  throw new IllegalArgumentException("Invalid id");
        List<Trip> trips = (List<Trip>) route.getTrips();
        return trips.stream().map(tripDTOMapper::apply).collect(Collectors.toList());
    }
}