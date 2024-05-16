package com.busstation.repositories;

import com.busstation.pojo.TransportationCompany;

import java.util.List;
import java.util.Map;

public interface TransportationCompanyRepository {
    List<TransportationCompany> list(Map<String,String> params) ;
    Long count(Map<String, String> params);
}
