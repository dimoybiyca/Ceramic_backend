package com.dimoybiyca.svitkeramicu.repository;

import com.dimoybiyca.svitkeramicu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findAllByRootId(Long rootId);
}
