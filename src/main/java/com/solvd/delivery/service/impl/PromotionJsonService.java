package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Promotion;
import com.solvd.delivery.service.interfaces.IPromotionJsonService;
import com.solvd.delivery.util.PromotionParser;

import java.io.IOException;
import java.util.List;

public class PromotionJsonService implements IPromotionJsonService {
    @Override
    public List<Promotion> loadPromotionsFromJson(String jsonPath) throws IOException {
        return PromotionParser.parse(jsonPath);
    }
}
