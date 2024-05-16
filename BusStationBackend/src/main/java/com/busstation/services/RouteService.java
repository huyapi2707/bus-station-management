package com.busstation.services;

import java.util.Map;

public interface RouteService {
    Map<String, Object> list(Map<String, String> params);
}
