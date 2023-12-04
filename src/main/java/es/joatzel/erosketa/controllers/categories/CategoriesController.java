package es.joatzel.erosketa.controllers.categories;
//maneja las solicitudes entrantes HTTP (GET, POST, PUT, DELETE, etc.) desde el cliente.

import es.joatzel.erosketa.dto.categories.CategoryRequestDto;
import es.joatzel.erosketa.dto.categories.CategoryResponseDto;
import es.joatzel.erosketa.mapper.categories.CategoryMapper;
import es.joatzel.erosketa.services.categories.CategoriesService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador de Categories
 * Aquí se implementan los métodos de la API REST
 * Es un controlador REST, por lo que se le indica con la anotación @RestController
 * El path base de la API REST es /api/categories y se le indica con la anotación @RequestMapping
 */
@RestController
@RequestMapping("/api/categories")
@Slf4j // Para el log
public class CategoriesController {
    // Mis dependecias
    private final CategoriesService categoriesService;
    private final CategoryMapper categoryMapper;

    // Inyectamos el repositorio de categories con la anotación @Autowired
    @Autowired
    public CategoriesController(CategoriesService categoriesService, CategoryMapper categoryMapper) {
        this.categoriesService = categoriesService;
        this.categoryMapper = categoryMapper;
    }

    // Aquí se implementan los métodos de la API REST

    // GET: /api/categories
    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(
            @RequestParam @Nullable String name
    ) {
        log.info("getAllCategories");

        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(
                    categoryMapper.toResponse(categoriesService.findAllByName(name))
            );
        }
        return ResponseEntity.ok(
                categoryMapper.toResponse(categoriesService.findAll())
        );
    }

    // GET: /api/categories/{id}
    // @PathVariable: Indica que el parámetro de la función es un parámetro de la URL en este caso {id}
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable Long id
    ) {
        log.info("getCategoryById");
        return ResponseEntity.ok(
                categoryMapper.toResponse(categoriesService.findById(id))
        );
    }


    @GetMapping("/find/{uuid}")
    public ResponseEntity<CategoryResponseDto> getCategoryByUuid(
            @PathVariable UUID uuid
    ) {
        log.info("getCategoryByUuid");
        // Existe la category?
        return ResponseEntity.ok(
                categoryMapper.toResponse(categoriesService.findByUuid(uuid))
        );
    }

    // POST: /api/categories
    // @RequestBody: Indica que el parámetro de la función es un parámetro del cuerpo de la petición HTTP
    @PostMapping
    public ResponseEntity<CategoryResponseDto> postCategory(
            @RequestBody CategoryRequestDto category
    ) {
        log.info("addCategory");
        // Añadimos la category
        var categorySaved = categoriesService.save(categoryMapper.toModel(category));
        // Devolvemos created
        return ResponseEntity.created(null).body(
                categoryMapper.toResponse(categorySaved)
        );
    }

    // PUT: /api/categories/{id}
    // @PathVariable: Indica que el parámetro de la función es un parámetro de la URL en este caso {id}
    // @RequestBody: Indica que el parámetro de la función es un parámetro del cuerpo de la petición HTTP
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> putCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto category
    ) {
        log.info("putCategory");
        // Devolvemos el OK
        return ResponseEntity.ok(
                categoryMapper.toResponse(categoriesService.update(id, categoryMapper.toModel(category)))
        );
    }

    // DELETE: /api/categories/{id}
    // @PathVariable: Indica que el parámetro de la función es un parámetro de la URL en este caso {id}
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(
            @PathVariable Long id
    ) {
        log.info("deleteCategory");
        categoriesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
