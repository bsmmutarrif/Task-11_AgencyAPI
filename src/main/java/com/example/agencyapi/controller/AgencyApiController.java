package com.example.agencyapi.controller;
import com.example.agencyapi.model.Agency;
import com.example.agencyapi.service.AgencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agency")public class AgencyApiController {
    @Autowired
    private AgencyService agencyService;
    @GetMapping("/all")
    public List<Agency> getAllAgencies() {
        return agencyService.getAllAgencies();
    } @
            PostMapping("/add")
    public String addAgency(@Valid @RequestBody Agency
                                    agency) {
        return agencyService.addAgency(agency);
    } @
            PutMapping("/update")
    public String updateAgency(@Valid @RequestBody Agency
                                       agency) {
        return agencyService.updateAgency(agency);
    }
}