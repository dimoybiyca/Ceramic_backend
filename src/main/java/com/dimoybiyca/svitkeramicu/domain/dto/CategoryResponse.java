package com.dimoybiyca.svitkeramicu.domain.dto;

public record CategoryResponse(
        Long id, String name, String imageUrl, Long rootId
) {
}
