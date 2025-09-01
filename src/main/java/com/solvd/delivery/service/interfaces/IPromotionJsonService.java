package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Promotion;

import java.io.IOException;
import java.util.List;

public interface IPromotionJsonService {
    List<Promotion> loadPromotionsFromJson(String jsonPath) throws IOException;
}
