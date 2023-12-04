package es.joatzel.erosketa.services.categories;

import es.joatzel.erosketa.models.categories.Category;

import java.util.List;
import java.util.UUID;

public interface CategoriesService {
    List<Category> findAll();

    Category findById(Long id);

    List<Category> findAllByName(String name);

    Category findByUuid(UUID uuid);

    Category update(Long id, Category category);

    Category save(Category category);

    void deleteById(Long id);

}
