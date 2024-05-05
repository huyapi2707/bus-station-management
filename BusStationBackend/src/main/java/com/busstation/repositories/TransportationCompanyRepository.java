package com.busstation.repositories;

import com.busstation.pojo.TransportationCompany;
import org.springframework.stereotype.Repository;


@Repository
public interface TransportationCompanyRepository {
    TransportationCompany getTransportationCompanyById(int id);
    void saveTransportationCompany(TransportationCompany newtransportationCompany);
    void deleteTransportationCompany(int id);
    void updateTransportationCompany(TransportationCompany transportationCompany);
}
