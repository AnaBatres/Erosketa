package es.joatzel.erosketa.dto.categories;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * DTO para crear una raqueta
 */
@Data
@NoArgsConstructor(force = true) // Para que no me de error en el constructor de jackson
@RequiredArgsConstructor // Para que me cree un constructor con los atributos finales
@Builder // Para poder usar el patrón Builder
public class CategoryRequestDto {
    private final String name;
    private final String description;
    private final String color;
}
