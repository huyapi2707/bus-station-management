package com.busstation.services.impl;

import com.busstation.pojo.TransportationCompany;
import com.busstation.repositories.TransportationCompanyRepository;
import com.busstation.services.TransportationCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransportationCompanyServiceImpl implements TransportationCompanyService {


    @Autowired
    private TransportationCompanyRepository transportationCompanyRepository;
    @Override
    public List<TransportationCompany> getAll(Map<String, String> params) {
        return transportationCompanyRepository.getAll(params);
    }
}
