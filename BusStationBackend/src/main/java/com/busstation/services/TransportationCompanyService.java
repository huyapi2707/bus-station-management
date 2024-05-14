package com.busstation.services;

import com.busstation.pojo.TransportationCompany;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface TransportationCompanyService{
    List<TransportationCompany> getAll(Map<String,String> params) ;
    Optional<TransportationCompany> getTransportationCompanyById(int id);
    TransportationCompany saveTransportationCompany(TransportationCompany transportationCompany);
    void updateTransportationCompany(TransportationCompany transportationCompany);
    void deleteTransportationCompany(int id);
    void verifiedTransportationCompany(int id);
}
