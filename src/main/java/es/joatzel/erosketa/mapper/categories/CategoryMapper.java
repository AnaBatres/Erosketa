package es.joatzel.erosketa.mapper.categories;

import es.joatzel.erosketa.dto.categories.CategoryRequestDto;
import es.joatzel.erosketa.dto.categories.CategoryResponseDto;
import es.joatzel.erosketa.models.categories.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CategoryMapper {

    // Aquí iran los metodos para mapear los DTOs a los modelos y viceversa

    // Mapeamos de modelo a DTO
    public CategoryResponseDto toResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getUuid(),
                category.getName(),
                category.getDescription(),
                category.getColor(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeleted()
        );
    }

    // Mapeamos de DTO a modelo
    public List<CategoryResponseDto> toResponse(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public Category toModel(CategoryRequestDto dto) {
        return new Category(
                0L,
                UUID.randomUUID(),
                dto.getName(),
                dto.getDescription(),
                dto.getColor(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false
        );
    }

    public Category toModelfromRequestDto(Long categoryId) {
        return new Category(
                categoryId,
                null,
                null,
                null,
                null,
                null,
                null,
                false
        );
    }

}
