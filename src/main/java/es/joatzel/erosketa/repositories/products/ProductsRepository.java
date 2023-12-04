package es.joatzel.erosketa.repositories.products;

import java.util.List;
import java.util.Optional;
import es.joatzel.erosketa.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


/**
 * Interfaz para los repositorios de Raquetas con las operaciones CRUD
 */
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByDescriptionContainsIgnoreCase(String description);

    Optional<Product> findByUuid(UUID uuid);
}


