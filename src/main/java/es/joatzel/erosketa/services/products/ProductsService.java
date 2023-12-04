package es.joatzel.erosketa.services.products;

import java.util.List;
import java.util.UUID;
import es.joatzel.erosketa.models.products.Product;

public interface ProductsService {

        List<Product> findAll();

        Product findById(Long id);

        Product findByUuid(UUID uuid);

        List<Product> findAllByName(String name);

        List<Product> findAllByDescription(String stock);

        Product update(Long id, Product product);

        Product save(Product product);

        void deleteById(Long id);

    }


