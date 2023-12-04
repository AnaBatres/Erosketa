package es.joatzel.erosketa.dto.products;

import es.joatzel.erosketa.dto.categories.CategoryResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true) // Para que no me de error en el constructor de jackson
@RequiredArgsConstructor // Para que me cree un constructor con los atributos finales
@Builder // Para poder usar el patrón Builder
//@JacksonXmlRootElement(localName = "product")
public class ProductResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer stock;
    private final CategoryResponseDto category;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Boolean deleted;
}
