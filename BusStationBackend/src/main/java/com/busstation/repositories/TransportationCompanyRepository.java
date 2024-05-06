package com.busstation.repositories;

import com.busstation.pojo.TransportationCompany;

import java.util.List;
import java.util.Map;

public interface TransportationCompanyRepository {
    List<TransportationCompany> getAll(Map<String,String> params) ;
    TransportationCompany getTransportationCompanyById(int id);
    void saveTransportationCompany(TransportationCompany newtransportationCompany);
    void deleteTransportationCompany(int id);
    void updateTransportationCompany(TransportationCompany transportationCompany);
}
