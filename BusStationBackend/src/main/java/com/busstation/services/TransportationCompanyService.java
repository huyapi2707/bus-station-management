package com.busstation.services;

import com.busstation.dtos.CompanyPublicDTO;
import com.busstation.pojo.TransportationCompany;

import java.util.List;
import java.util.Map;

public interface TransportationCompanyService{
    Map<String, Object> list(Map<String,String> params) ;
    List<CompanyPublicDTO> getAllNameAndId();

}
