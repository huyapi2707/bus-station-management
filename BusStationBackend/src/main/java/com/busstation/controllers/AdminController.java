package com.busstation.controllers;

import com.busstation.dtos.UserDTO;
import com.busstation.pojo.User;
import com.busstation.services.TransportationCompanyService;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.busstation.pojo.TransportationCompany;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/companies")
    public String companies() {
        return "companies";
    }

    @GetMapping("/company")
    public String companyForm() {
        return "company";
    }

    @GetMapping("/company_detail")
    public String company_detail() {
        return "company_detail";
    }

    @GetMapping("/routes")
    public String routes() {
        return "routes";
    }


    @GetMapping("/stations")
    public String stations() {
        return "stations";
    }

    @GetMapping("/station_detail")
    public String station_detail() {
        return "station_detail";
    }

    @GetMapping("/addstation")
    public String addstation() {
        return "addstation";
    }

    @GetMapping("/users/role/{id}")
    public ResponseEntity<List<UserDTO>> findActiveUsersByRoleId(@PathVariable Long id) {
        List<UserDTO> users = userService.findActiveUsersByRoleId(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/verify")
    public String verify() {
        return "verify";
    }

}
