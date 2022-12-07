package com.dimoybiyca.svitkeramicu.domain.dto;

import java.util.Map;

public record ProductResponse(
       Long id,
       String name,
       String description,
       double unitsInStock,
       String unitType,
       double unitPrice,
       String imageUrl,
       String status,
       String manufacturer,
       String country,
       Map<String, String> properties
) {
}
