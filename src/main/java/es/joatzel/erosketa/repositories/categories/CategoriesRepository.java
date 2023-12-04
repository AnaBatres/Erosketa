package es.joatzel.erosketa.repositories.categories;

import es.joatzel.erosketa.models.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaz para los repositorios de Raquetas con las operaciones CRUD
 * Usamos la interfaz  JpaRepository de Spring Data
 * https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    // Aquí puedes añadir métodos propios de la clase Category
    // Genera automáticamente las consultas
    // https://www.baeldung.com/spring-data-derived-queries
    List<Category> findByNameContainsIgnoreCase(String name);

    // Otra forma es hacerlo con @Query
    @Query("SELECT r FROM Category r WHERE r.name LIKE %?1%")
    List<Category> findAllByModeloContainsIgnoreCase(String name);

    Optional<Category> findByUuid(UUID uuid);

}