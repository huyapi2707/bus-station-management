package com.busstation.services.impl;

import com.busstation.dtos.CompanyPublicDTO;
import com.busstation.dtos.mappers.CompanyPublicMapper;
import com.busstation.pojo.TransportationCompany;
import com.busstation.repositories.TransportationCompanyRepository;
import com.busstation.services.TransportationCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:configuration.properties")
public class TransportationCompanyServiceImpl implements TransportationCompanyService {

    @Autowired
    private Environment environment;


    @Autowired
    private TransportationCompanyRepository repository;

    @Autowired
    private CompanyPublicMapper companyPublicMapper;


    @Override
    public Map<String, Object> list(Map<String, String> params) {
        List<TransportationCompany> results = repository.list(params);
        Long total = repository.count(params);
        int pageSize = Integer.parseInt(environment.getProperty("transportationCompany.pageSize"));
        int pageTotal = (int) Math.ceil(total / pageSize);
        Map<String, Object> m = new HashMap<>();
        m.put("total", total);
        m.put("pageTotal", pageTotal);
        m.put("results", results);
        return m;

    }

    @Override
    public List<CompanyPublicDTO> getAllNameAndId() {
       List<TransportationCompany> results = repository.list(new HashMap<>());
       List<CompanyPublicDTO> r = results.stream().map(companyPublicMapper::apply).collect(Collectors.toList());
       return r;
    }
}
