package com.busstation.services;


import com.busstation.dtos.TicketDTO;

import java.util.List;
import java.util.Map;

public interface TicketService {


    List<TicketDTO> getInfoFromCart(List<Map<String, String>> clientCart);

}
