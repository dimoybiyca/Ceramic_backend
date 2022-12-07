package com.dimoybiyca.svitkeramicu.domain.mapper;

import com.dimoybiyca.svitkeramicu.domain.dto.ProductResponse;
import com.dimoybiyca.svitkeramicu.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public ProductResponse toView(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getUnitsInStock(),
                product.getUnitType(),
                product.getUnitPrice(),
                product.getImageUrl(),
                product.getStatus(),
                product.getManufacturer(),
                product.getCountry(),
                product.getProperties()
        );
    }

    public List<ProductResponse> listToView(List<Product> products) {
        List<ProductResponse> result = new ArrayList<>();

        for(Product product : products) {
            result.add(this.toView(product));
        }

        return result;
    }
}
