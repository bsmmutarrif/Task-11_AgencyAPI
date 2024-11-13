package com.example.agencyapi.service;
import com.example.agencyapi.model.Agency;
import com.example.agencyapi.util.AgencyJsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;@Service
public class AgencyService {
    @Value("${agency.updates-allowed}")
    private boolean updatesAllowed;
    /**
     * Get all agencies from the JSON file.
     */
    public List<Agency> getAllAgencies() {
        return AgencyJsonUtils.readAgencies();
    }
            /**
            * Add a new agency. If an agency with the same 'name' or 'code'
    exists, return an error message.
*/
    public String addAgency(Agency newAgency) {
        List<Agency> agencies = AgencyJsonUtils.readAgencies();
        boolean exists = agencies.stream().anyMatch(agency ->
                agency.getName().equalsIgnoreCase(newAgency.getName()) ||
                        agency.getCode().equalsIgnoreCase(newAgency.getCode())
        );
        if (exists) {
            return "Cannot add agency: An agency with the same name or code already exists.";
        }
        agencies.add(newAgency);
        AgencyJsonUtils.writeAgencies(agencies);
        return "Agency added successfully.";
    }

            /**
            * Update an existing agency. If updates are not allowed or the
    agency is not found, return an error message.
*/
    public String updateAgency(Agency updatedAgency) {
        if (!updatesAllowed) {
            return "Update functionality is disabled.";
        }
        List<Agency> agencies = AgencyJsonUtils.readAgencies();
        Optional<Agency> existingAgency = agencies.stream()
                .filter(agency ->
                        agency.getId().equals(updatedAgency.getId()))
                .findFirst();if (existingAgency.isEmpty()) {
            return "No agency found with the given ID.";
        }
        agencies.remove(existingAgency.get());
        agencies.add(updatedAgency);
        AgencyJsonUtils.writeAgencies(agencies);
        return "Agency updated successfully.";
    }
}