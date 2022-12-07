package com.dimoybiyca.svitkeramicu.service;

import com.dimoybiyca.svitkeramicu.domain.dto.ProductResponse;
import com.dimoybiyca.svitkeramicu.domain.exception.NullEntityReferenceException;
import com.dimoybiyca.svitkeramicu.domain.mapper.ProductMapper;
import com.dimoybiyca.svitkeramicu.model.Product;
import com.dimoybiyca.svitkeramicu.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public List<ProductResponse> readByCategoryPaginate(Long categoryId, int page){
        if (categoryId == null) {
            throw new NullEntityReferenceException("Category id cannot be null");
        }

        Pageable pageable = PageRequest.of(page, 30);
        return productMapper.listToView(productRepo.findAllByCategoryId(categoryId, pageable));
    }

    public Product create(Product product) {
        if(product != null) {
            return productRepo.save(product);
        }

        throw new NullEntityReferenceException("Product cannot be null");
    }

    public Product readById(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        format("Product with id %s not found", id))
        );
    }

    public Product update(Product product) {
        if(product != null && productRepo.findById(product.getId()).isPresent()) {
            return productRepo.save(product);
        }

        throw new NullEntityReferenceException("Product cannot be null");
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}