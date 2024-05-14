package com.busstation.services.impl;

import com.busstation.pojo.Route;
import com.busstation.repositories.RouteRepository;
import com.busstation.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Route> getAllRoutes(Map<String, String> params) {
        return routeRepository.getAll(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Route getRouteById(Long id) {
        return routeRepository.getRouteById(id);
    }

    @Override
    @Transactional
    public void saveRoute(Route route) {
        routeRepository.saveRoute(route);
    }


    @Override
    @Transactional
    public void updateRoute(Route route) {
        routeRepository.updateRoute(route);
    }

    @Override
    @Transactional
    public void deleteRoute(Long id) {
        routeRepository.deleteRoute(id);
    }
}
