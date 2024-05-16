package com.busstation.repositories;

import com.busstation.pojo.Route;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

public interface RouteRepository  {
   List<Route> list(Map<String, String> params);
    Long count(Map<String, String> params);
}
