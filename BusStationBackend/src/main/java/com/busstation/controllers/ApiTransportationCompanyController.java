package com.busstation.controllers;

import com.busstation.pojo.TransportationCompany;
import com.busstation.services.TransportationCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transportation_company")
public class ApiTransportationCompanyController {

    @Autowired
    private TransportationCompanyService transportationCompanyService;


    @GetMapping("/list")
    public ResponseEntity<Object> list(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(transportationCompanyService.list(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportationCompany> getTransportationCompanyById(@PathVariable int id) {
        return transportationCompanyService.getTransportationCompanyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<TransportationCompany> createTransportationCompany(@RequestBody TransportationCompany transportationCompany) {
        TransportationCompany newCompany = transportationCompanyService.saveTransportationCompany(transportationCompany);
        return ResponseEntity.ok(newCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransportationCompany(@PathVariable int id, @RequestBody TransportationCompany transportationCompany) {
        if (!transportationCompanyService.getTransportationCompanyById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        transportationCompany.setId((long) id);
        transportationCompanyService.updateTransportationCompany(transportationCompany);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransportationCompany(@PathVariable int id) {
        transportationCompanyService.deleteTransportationCompany(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/name-and-id")
    public ResponseEntity<Object> listNameAndId(){
        return ResponseEntity.ok(transportationCompanyService.getAllNameAndId());
    }
}
