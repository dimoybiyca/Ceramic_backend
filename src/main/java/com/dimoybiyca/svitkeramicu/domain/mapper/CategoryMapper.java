package com.dimoybiyca.svitkeramicu.domain.mapper;

import com.dimoybiyca.svitkeramicu.domain.dto.CategoryResponse;
import com.dimoybiyca.svitkeramicu.domain.dto.CreateCategoryRequest;
import com.dimoybiyca.svitkeramicu.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public Category toObject(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setImageUrl(request.imageUrl());
        category.setRootId(request.rootId());

        return category;
    }

    public CategoryResponse toView(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getImageUrl(),
                category.getRootId()
        );
    }

    public List<CategoryResponse> listToView(List<Category> categories) {
        List<CategoryResponse> result = new ArrayList<>();

        for(Category category : categories) {
            result.add(this.toView(category));
        }

        return result;
    }
}
