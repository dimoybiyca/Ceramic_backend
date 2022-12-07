package com.dimoybiyca.svitkeramicu.repository;

import com.dimoybiyca.svitkeramicu.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
}
