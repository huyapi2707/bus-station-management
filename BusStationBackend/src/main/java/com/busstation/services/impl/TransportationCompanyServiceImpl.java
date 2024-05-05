package com.busstation.services.impl;

import com.busstation.pojo.TransportationCompany;
import com.busstation.repositories.TransportationCompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.busstation.services.TransportationCompanyService;

@Service("ManageTransportationCompany")
public class TransportationCompanyServiceImpl implements TransportationCompanyService {

    @Autowired
    private TransportationCompanyRepository transportationCompanyRepository;

    @Override
    public Optional<TransportationCompany> getTransportationCompanyById(int id) {
        return Optional.ofNullable(transportationCompanyRepository.getTransportationCompanyById(id));
    }

    @Override
    public TransportationCompany saveTransportationCompany(TransportationCompany transportationCompany) {
        transportationCompanyRepository.saveTransportationCompany(transportationCompany);
        return transportationCompany; // Assuming the ID is generated and set in the transportationCompany object.
    }

    @Override
    public void updateTransportationCompany(TransportationCompany transportationCompany) {
        transportationCompanyRepository.updateTransportationCompany(transportationCompany);
    }

    @Override
    public void deleteTransportationCompany(int id) {
        transportationCompanyRepository.deleteTransportationCompany(id);
    }
}
