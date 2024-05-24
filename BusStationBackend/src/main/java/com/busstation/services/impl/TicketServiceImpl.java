package com.busstation.services.impl;

import com.busstation.dtos.RouteDTO;
import com.busstation.dtos.TicketDTO;
import com.busstation.dtos.TripDTO;
import com.busstation.pojo.Route;
import com.busstation.pojo.Seat;
import com.busstation.repositories.SeatRepository;
import com.busstation.services.RouteService;
import com.busstation.services.TicketService;
import com.busstation.services.TripService;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private RouteService routeService;

    @Autowired
    private TripService tripService;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<TicketDTO> getInfoFromCart(List<Map<String, String>> clientCart) {
        List<TicketDTO> results = new ArrayList<>();
        clientCart.forEach(c -> {
            try {
                Long routeId = Long.parseLong(c.get("routeId"));
                Long tripId = Long.parseLong(c.get("tripId"));
                Long seatId = Long.parseLong(c.get("seatId"));

                Boolean withCargo = Boolean.valueOf(c.get("withCargo"));

                RouteDTO routeInfo = routeService.getById(routeId);
                if (!withCargo.booleanValue()) {
                    routeInfo.setCargoPrice(0.0);
                }
                TripDTO tripInfo = tripService.tripInfo(tripId);
                Seat seat = seatRepository.getById(seatId);
                TicketDTO newTicket = TicketDTO.builder()
                        .routeInfo(routeInfo)
                        .tripInfo(tripInfo)
                        .seat(seat)
                        .build();
                results.add(newTicket);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        });
        return results;
    }
}
