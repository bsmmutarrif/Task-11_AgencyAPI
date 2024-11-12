package com.example.agencyapi.controller;

import com.example.agencyapi.model.Agency;
import com.example.agencyapi.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agency")
public class AgencyApiController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping("/all")
    public List<Agency> getAllAgencies() {
        return agencyService.getAllAgencies();
    }

    @
            PostMapping("/add")
    public String addAgency(@RequestBody Agency agency) {
        return agencyService.addOrUpdateAgency(agency);
    }

    @
            PutMapping("/update")
    public String updateAgency(@RequestBody Agency agency) {
        return agencyService.addOrUpdateAgency(agency);
    }
}