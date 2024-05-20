package com.busstation.dtos;

import com.busstation.pojo.Seat;
import com.busstation.pojo.Trip;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TicketDTO {
    private String ticketId;
    private RouteDTO routeInfo;

    private Seat seat;

    private TripDTO tripInfo;
    private String paymentMethod;

}
