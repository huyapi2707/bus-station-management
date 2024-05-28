package com.busstation.repositories;

import com.busstation.pojo.OnlinePaymentResult;
import com.busstation.pojo.Ticket;

import java.util.List;

public interface TicketRepository {
    void saveAll(List<Ticket> tickets);
    void updateAll(List<Ticket> tickets);
}
