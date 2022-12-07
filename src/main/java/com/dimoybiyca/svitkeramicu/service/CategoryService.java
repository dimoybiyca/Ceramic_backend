package com.dimoybiyca.svitkeramicu.service;

import com.dimoybiyca.svitkeramicu.domain.exception.NullEntityReferenceException;
import com.dimoybiyca.svitkeramicu.model.Category;
import com.dimoybiyca.svitkeramicu.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CrossOrigin(maxAge = 36000)
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Transactional
    public Category create(Category category) {
        if(category != null) {
            return categoryRepo.save(category);
        }

        throw new NullEntityReferenceException("Category cannot be null");
    }

    public Category readById(Long id) {
        return categoryRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        format("Category with id %s not found", id))
        );
    }

    public List<Category> readByRootId(Long rootId) {
        return categoryRepo.findAllByRootId(rootId);
    }

    @Transactional
    public Category update(Category category) {
        if(category != null && categoryRepo.existsById(category.getId())) {
            return categoryRepo.save(category);
        }

        throw new NullEntityReferenceException("Category cannot be null");
    }

    @Transactional
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }
}
