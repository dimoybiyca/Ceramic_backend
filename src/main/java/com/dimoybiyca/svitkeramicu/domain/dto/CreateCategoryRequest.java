package com.dimoybiyca.svitkeramicu.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateCategoryRequest(
        @NotBlank String name,
        @NotBlank String imageUrl,
        @NotNull Long rootId
) {}
