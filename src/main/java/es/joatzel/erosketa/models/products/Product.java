package es.joatzel.erosketa.models.products;


import com.fasterxml.jackson.annotation.JsonBackReference;
import es.joatzel.erosketa.models.categories.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor // Necesario para JPA
@AllArgsConstructor
@Builder // Para poder usar el patrón Builder
@Entity // Para que sea una entidad de JPA
@Table(name = "products")
public class Product {
    @Id // Indicamos que es el ID de la tabla
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private Integer stock;

    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
//    @JsonBackReference // Evitamos recursividad
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted = false;

}
