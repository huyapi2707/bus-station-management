package com.busstation.services;

import com.busstation.dtos.CompanyPublicDTO;
import com.busstation.pojo.TransportationCompany;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface TransportationCompanyService{

    Map<String, Object> list(Map<String,String> params) ;
    List<CompanyPublicDTO> getAllNameAndId();


    Optional<TransportationCompany> getTransportationCompanyById(int id);
    TransportationCompany saveTransportationCompany(TransportationCompany transportationCompany);
    void updateTransportationCompany(TransportationCompany transportationCompany);
    void deleteTransportationCompany(int id);

}
