package es.joatzel.erosketa.dto.categories;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para devolver una raqueta
 */

@Data
@NoArgsConstructor(force = true) // Para que no me de error en el constructor de jackson
@RequiredArgsConstructor // Para que me cree un constructor con los atributos finales
public class CategoryResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
    private final String description;
    private final String color;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Boolean deleted;
}
