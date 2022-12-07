package com.dimoybiyca.svitkeramicu.controler;

import com.dimoybiyca.svitkeramicu.domain.dto.CategoryResponse;
import com.dimoybiyca.svitkeramicu.domain.dto.CreateCategoryRequest;
import com.dimoybiyca.svitkeramicu.domain.mapper.CategoryMapper;
import com.dimoybiyca.svitkeramicu.model.Category;
import com.dimoybiyca.svitkeramicu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/public/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("{id}")
    public CategoryResponse getCategory(@PathVariable Long id) {
        return categoryMapper.toView(categoryService.readById(id));
    }

    @GetMapping("{id}/sub")
    public List<CategoryResponse> getSubCategories(@PathVariable("id") Long rootId) {

        System.out.println(categoryService.readByRootId(rootId));

        return categoryMapper.listToView(
                categoryService.readByRootId(rootId)
        );
    }

    @PostMapping("create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CreateCategoryRequest request,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException("//TODO");
        }

        Category category = categoryMapper.toObject(request);
        categoryService.create(category);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
