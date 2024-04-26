package com.busstation.services;

import com.busstation.pojo.TransportationCompany;

import java.util.Optional;

public interface TransportationCompanyService {
    Optional<TransportationCompany> getTransportationCompanyById(int id);
    TransportationCompany saveTransportationCompany(TransportationCompany transportationCompany);
    void updateTransportationCompany(TransportationCompany transportationCompany);
    void deleteTransportationCompany(int id);
}
