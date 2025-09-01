package com.solvd.delivery.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.delivery.model.Promotion;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PromotionParser {
    public static List<Promotion> parse(String jsonPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
 
        return mapper.readValue(new File(jsonPath), new TypeReference<>() {
        });
    }
}
