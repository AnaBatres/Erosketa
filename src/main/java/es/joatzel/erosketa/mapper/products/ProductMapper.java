package es.joatzel.erosketa.mapper.products;

import es.joatzel.erosketa.mapper.categories.CategoryMapper;
import org.springframework.stereotype.Component;
import es.joatzel.erosketa.dto.products.ProductResponseDto;
import es.joatzel.erosketa.dto.products.ProductRequestDto;
import es.joatzel.erosketa.models.products.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;
    // Aquí iran los metodos para mapear los DTOs a los modelos y viceversa

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    // Mapeamos de modelo a DTO
    public ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getUuid(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory() != null ? categoryMapper.toResponse(product.getCategory()) : null,
                product.getCreatedAt(),
                product.getUpdatedAt(),
                false
        );
    }

    // Mapeamos de DTO a modelo
    public List<ProductResponseDto> toResponse(List<Product> products) {
        return products.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public Product toModel(ProductRequestDto dto) {
        return new Product(
                0L,
                UUID.randomUUID(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock(),
                dto.getCategoryId() != null ? categoryMapper.toModelfromRequestDto(dto.getCategoryId()) : null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                false
        );
    }
}
