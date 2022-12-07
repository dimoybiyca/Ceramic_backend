package com.dimoybiyca.svitkeramicu.controler;

import com.dimoybiyca.svitkeramicu.domain.dto.ProductResponse;
import com.dimoybiyca.svitkeramicu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{categoryId}")
    public List<ProductResponse> getProductPaginate(@PathVariable Long categoryId) {
        return productService.readByCategoryPaginate(categoryId, 0);
    }
}
