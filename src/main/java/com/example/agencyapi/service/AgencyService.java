package com.example.agencyapi.service;
import com.example.agencyapi.model.Agency;
import com.example.agencyapi.util.AgencyJsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AgencyService {

    @Value("${agency.updates-allowed}")
    private boolean updatesAllowed;

    public List<Agency> getAllAgencies() {
        return AgencyJsonUtils.readAgencies();}
    public String addOrUpdateAgency(Agency newAgency) {
        List<Agency> agencies = AgencyJsonUtils.readAgencies();
        Optional<Agency> existingAgency = agencies.stream()
                .filter(agency ->
                        agency.getId().equals(newAgency.getId()))
                .findFirst();
        if (existingAgency.isPresent()) {
            if (!updatesAllowed) {
                return "Update functionality is disabled.";
            }
            agencies.remove(existingAgency.get());
            agencies.add(newAgency);
            AgencyJsonUtils.writeAgencies(agencies);
            return "Agency updated successfully.";
        } else {
            agencies.add(newAgency);
            AgencyJsonUtils.writeAgencies(agencies);
            return "Agency added successfully.";
        }}
}