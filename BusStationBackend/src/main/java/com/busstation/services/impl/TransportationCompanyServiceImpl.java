package com.busstation.services.impl;

import com.busstation.dtos.CompanyPublicDTO;
import com.busstation.mappers.CompanyPublicMapper;
import com.busstation.pojo.TransportationCompany;
import com.busstation.repositories.TransportationCompanyRepository;
import com.busstation.services.EmailService;
import com.busstation.services.TransportationCompanyService;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@PropertySource("classpath:configuration.properties")
public class TransportationCompanyServiceImpl implements TransportationCompanyService {

    @Autowired
    private Environment environment;


    @Autowired
    private TransportationCompanyRepository repository;

    @Autowired
    private CompanyPublicMapper companyPublicMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;



    @Override
    public Map<String, Object> list(Map<String, String> params) {
        List<TransportationCompany> results = repository.list(params);
        Long total = repository.count(params);
        int pageSize = Integer.parseInt(environment.getProperty("transportationCompany.pageSize"));
        int pageTotal = (int) ((total / pageSize) + 1);
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

    @Override
    public Optional<TransportationCompany> getTransportationCompanyById(Long id) {
        return Optional.ofNullable(repository.getTransportationCompanyById(id));
    }

    @Override
    public TransportationCompany saveTransportationCompany(TransportationCompany transportationCompany) {
        repository.saveTransportationCompany(transportationCompany);
        return transportationCompany;
    }

    @Transactional
    @Override
    public void updateTransportationCompany(TransportationCompany company) {
        Optional<TransportationCompany> existingCompanyOpt = repository.findById(company.getId());
        if (existingCompanyOpt.isPresent()) {
            TransportationCompany existingCompany = existingCompanyOpt.get();
            existingCompany.setName(company.getName());
            existingCompany.setAvatar(company.getAvatar());
            existingCompany.setPhone(company.getPhone());
            existingCompany.setEmail(company.getEmail());
            existingCompany.setIsVerified(company.getIsVerified());
            existingCompany.setIsActive(company.getIsActive());
            existingCompany.setIsCargoTransport(company.getIsCargoTransport());
            existingCompany.setManager(company.getManager());

            repository.save(existingCompany);
        } else {
            throw new RuntimeException("Company not found");
        }
    }

    @Override
    @Transactional
    public void deleteTransportationCompany(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<TransportationCompany> getUnverifiedCompanies() {
        return repository.findByIsVerifiedFalse();
    }

    @Override
    @Transactional
    public void verifyCompany(Long id) {
        repository.verifyCompany(id);
        userService.changeRole(id,(long)3);
        Optional<TransportationCompany> companyOpt = repository.findById(id);
        if (companyOpt.isPresent()) {
            TransportationCompany company = companyOpt.get();
            String to = company.getEmail();
            String subject = "Your Company has been Verified";
            String text = "Dear " + company.getName() + ",\n\nYour company has been successfully verified.\n\nBest regards,\nBus Station Team";
            emailService.sendEmail(to, subject, text);
        }
    }
}