package com.busstation.services;


import com.busstation.dtos.CheckoutResponse;
import com.busstation.dtos.TicketDTO;
import com.busstation.pojo.Ticket;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface TicketService {


    List<TicketDTO> getInfoFromCart(List<Map<String, String>> clientCart);

    List<Ticket> createTickets(List<Map<String, String>> client_cart, Long payment_method_id);

    CheckoutResponse checkout(List<Map<String, String>> client_cart, Long paymentMethodId, String ip) throws UnsupportedEncodingException;

    String handleVnPayResponse(Map<String, String> params) throws ParseException;
}
