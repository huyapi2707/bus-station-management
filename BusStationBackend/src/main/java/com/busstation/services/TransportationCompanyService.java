package com.busstation.services;

import com.busstation.pojo.TransportationCompany;

import java.util.List;
import java.util.Map;

public interface TransportationCompanyService{
    List<TransportationCompany> getAll(Map<String,String> params) ;


}
