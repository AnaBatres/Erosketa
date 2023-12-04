package es.joatzel.erosketa.models.categories;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor // Necesario para JPA
@AllArgsConstructor
@Builder // Para poder usar el patrón Builder
@Entity// Para que sea una entidad de JPA
@Table(name = "categories") // Para indicar la tabla de la BD, si no coge el nombre de la clase
public class Category {
     @Id // Indicamos que es el ID de la tabla
     @GeneratedValue(strategy = GenerationType.AUTO) // Indicamos que es el ID generado automáticamente
     private Long id;
     private UUID uuid = UUID.randomUUID();
     private String name;
     private String description;
     private String color;

     @Temporal(TemporalType.TIMESTAMP) // Indicamos que es un campo de tipo fecha y hora
     @CreatedDate
     private LocalDateTime createdAt = LocalDateTime.now();
     @Temporal(TemporalType.TIMESTAMP) // Indicamos que es un campo de tipo fecha y hora
     @LastModifiedDate
     private LocalDateTime updatedAt = LocalDateTime.now();
     private Boolean deleted = false;

}
