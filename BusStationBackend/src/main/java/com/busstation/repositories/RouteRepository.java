package com.busstation.repositories;

import com.busstation.pojo.Route;
import java.util.List;
import java.util.Map;

public interface RouteRepository {
    List<Route> getAll(Map<String, String> params);
    Route getRouteById(Long id);
    void saveRoute(Route newRoute);
    void deleteRoute(Long id);
    void updateRoute(Route route);
}
