
package com.busstation.controllers;

import com.busstation.pojo.TransportationCompany;
import com.busstation.services.TransportationCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/transportation-companies")
public class ManageBusCompanyController {
    private TransportationCompanyService transportationCompanyService;

    @Autowired
    public ManageBusCompanyController(TransportationCompanyService transportationCompanyService) {
        this.transportationCompanyService = transportationCompanyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportationCompany> getTransportationCompanyById(@PathVariable int id) {
        return transportationCompanyService.getTransportationCompanyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
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

    @PostMapping("/{id}/verify")
    public String verifyCompany(@PathVariable int id) {
        transportationCompanyService.verifiedTransportationCompany(id);
        return "Company verification successfully!";
    }
}
