package es.joatzel.erosketa.dto.products;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor(force = true) // Para que no me de error en el constructor de jackson
@RequiredArgsConstructor // Para que me cree un constructor con los atributos finales
@Builder // Para poder usar el patrón Builder
public class ProductRequestDto {
    private final String name;
    private final String description;
    private final Double price;
    private final Integer stock;
    private final Long categoryId;
}