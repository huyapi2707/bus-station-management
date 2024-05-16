package com.busstation.services.impl;

import com.busstation.dtos.RouteDTO;
import com.busstation.dtos.mappers.RouteDTOMapper;
import com.busstation.pojo.Route;
import com.busstation.repositories.RouteRepository;
import com.busstation.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
}
