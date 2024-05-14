package com.busstation.services;

import com.busstation.pojo.Route;
import java.util.List;
import java.util.Map;

public interface RouteService {
    List<Route> getAllRoutes(Map<String, String> params);
    Route getRouteById(Long id);
    void saveRoute(Route route);
    void updateRoute(Route route);
    void deleteRoute(Long id);
}
