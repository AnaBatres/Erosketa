package es.joatzel.erosketa.services.products;

import es.joatzel.erosketa.models.categories.Category;
import lombok.extern.slf4j.Slf4j;
import es.joatzel.erosketa.models.products.Product;
import es.joatzel.erosketa.repositories.categories.CategoriesRepository;
import es.joatzel.erosketa.repositories.products.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@CacheConfig(cacheNames = {"products"}) // Nombre del caché
    public class ProductsServiceImpl implements ProductsService {
        // Mis dependencias
        private final ProductsRepository productsRepository;
        private final CategoriesRepository categoriesRepository;

        @Autowired
        // Inyectamos los repositorios
        public ProductsServiceImpl(ProductsRepository productsRepository, CategoriesRepository categoriesService) {
            this.productsRepository = productsRepository;
            this.categoriesRepository = categoriesService;
        }

        @Override
        @Cacheable // Indicamos que se cachee, no es recomendable si hay muchos!!
        public List<Product> findAll() {
            log.info("findAll");
            return productsRepository.findAll();
        }

        @Override
        @Cacheable // Indicamos que se cachee
        public Product findById(Long id) {
            log.info("findById");
            return productsRepository.findById(id).orElse(null);
        }

        @Override
        @Cacheable // Indicamos que se cachee
        public Product findByUuid(UUID uuid) {
            log.info("findByUuid");
            return productsRepository.findByUuid(uuid).orElse(null);
        }

        @Override
        public List<Product> findAllByDescription(String description) {
            log.info("findAllByDescription");
            return productsRepository.findByDescriptionContainsIgnoreCase(description);
        }

        @Override
        public List<Product> findAllByName(String name) {
            log.info("findAllByName");
            return productsRepository.findByNameContainsIgnoreCase(name);
        }

        @Override
        @CachePut // Indicamos que se actualice el caché
        public Product update(Long id, Product product) {
            log.info("update");
            // existe el id?
            var updated = this.findById(id);
            Category category = categoriesRepository.findById(product.getCategory().getId()).orElse(null);


                    // validamos --> Ya lo estamos haciendo con @Valid en el controlador
            // raquetaValidator.validate(raqueta);

            // asignamos los nuevos valores
            updated.setName(product.getName());
            updated.setDescription(product.getDescription());
            updated.setPrice(product.getPrice());
            updated.setStock(product.getStock());
            updated.setCategory(category);
            updated.setUpdatedAt(LocalDateTime.now());

            // Si todo va bien guardamos
            return productsRepository.save(updated);
        }
        @Override
        @CachePut // Indicamos que se actualice el caché
        public Product save(Product product) {
            log.info("save");
            return productsRepository.save(product);
        }

        @Override
        @CacheEvict // Indicamos que se elimine el caché
        public void deleteById(Long id) {
            log.info("deleteById");
            this.findById(id);
            productsRepository.deleteById(id);
        }
    }

