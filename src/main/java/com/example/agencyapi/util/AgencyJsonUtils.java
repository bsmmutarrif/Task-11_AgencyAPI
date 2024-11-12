package com.example.agencyapi.util;
import com.example.agencyapi.model.Agency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgencyJsonUtils {
    private static final String FILE_PATH = "agencies.json";
    private static final ObjectMapper objectMapper = new
            ObjectMapper();
    public static List<Agency> readAgencies() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        } try {
            return objectMapper.readValue(file, new
                    TypeReference<List<Agency>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading agencies file",e);
        }}
    public static void writeAgencies(List<Agency> agencies) {
        try {objectMapper.writeValue(new File(FILE_PATH), agencies);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to agencies file", e);
        }
    }}