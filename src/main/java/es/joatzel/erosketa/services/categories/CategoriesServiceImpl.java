package es.joatzel.erosketa.services.categories;

import es.joatzel.erosketa.models.categories.Category;
import es.joatzel.erosketa.repositories.categories.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
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
@CacheConfig(cacheNames = {"categories"}) // Nombre del caché
public class CategoriesServiceImpl implements CategoriesService {
    // Mis repositorios
    private final CategoriesRepository categoriesRepository;

    // Inyectamos los repositorios
    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    @Cacheable // Indicamos que se cachee, no es recomendable si hay muchos!!
    public List<Category> findAll() {
        log.info("findAll");
        return categoriesRepository.findAll();
    }

    @Override
    @Cacheable // Indicamos que se cachee
    public Category findById(Long id) {
        log.info("findById");
        return categoriesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllByName(String name) {
        log.info("findAllByName");
        return categoriesRepository.findByNameContainsIgnoreCase(name);
    }
    @Override
    @Cacheable
    public Category findByUuid(UUID uuid) {
        log.info("findByUuid");
        return categoriesRepository.findByUuid(uuid).orElse(null);
    }

    @Override
    @CachePut // Indicamos que se actualice el caché
    public Category update(Long id, Category category) {
        log.info("update");
        // existe el id?
        var updated = this.findById(id);

        // validamos --> Ya lo estamos haciendo con @Valid en el controlador
        // raquetaValidator.validate(raqueta);

        // asignamos los nuevos valores
        updated.setName(category.getName());
        updated.setDescription(category.getDescription());
        updated.setColor(category.getColor());
        updated.setUpdatedAt(LocalDateTime.now());

        // Si todo va bien guardamos
        return categoriesRepository.save(updated);
    }

    @Override
    @CachePut // Indicamos que se actualice el caché
    public Category save(Category category) {
        log.info("save");
        return categoriesRepository.save(category);
    }

    @Override
    @CacheEvict // Indicamos que se elimine del caché
    public void deleteById(Long id) {
        log.info("deleteById");
        categoriesRepository.deleteById(id);
    }
}
