package es.joatzel.erosketa.controllers.products;


import es.joatzel.erosketa.dto.products.ProductRequestDto;
import es.joatzel.erosketa.dto.products.ProductResponseDto;
import es.joatzel.erosketa.mapper.products.ProductMapper;
import es.joatzel.erosketa.services.products.ProductsService;
import es.joatzel.erosketa.services.categories.CategoriesService;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Slf4j // Para el log
public class ProductsController {
    private final ProductsService productsService;
    //private final CategoriesService categoriesService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductsController(ProductsService productsService, CategoriesService categoriesService, ProductMapper productMapper) {
        this.productsService = productsService;
        this.productMapper = productMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
            @RequestParam @Nullable String name,
            @RequestParam @Nullable String description
    ) {
        log.info("getAllProducts");

        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(
                    productMapper.toResponse(productsService.findAllByName(name))
            );
        }

        if (description != null) {
            return ResponseEntity.ok(
                    productMapper.toResponse(productsService.findAllByDescription(description))
            );
        }

        return ResponseEntity.ok(
                productMapper.toResponse(productsService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Long id
    ) {
        log.info("getProductById");
        // Existe el product?
        return ResponseEntity.ok(
                productMapper.toResponse(productsService.findById(id))
        );
    }

    @GetMapping("/find/{uuid}")
    public ResponseEntity<ProductResponseDto> getProductByUuid(
            @PathVariable UUID uuid
    ) {
        log.info("getProductByUuid");
        // Existe el product?
        return ResponseEntity.ok(
                productMapper.toResponse(productsService.findByUuid(uuid))
        );
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDto> postProduct(
            @RequestBody ProductRequestDto product
    ) {
        log.info("addProduct");
        return ResponseEntity.created(null).body(
                productMapper.toResponse(
                        productsService.save(productMapper.toModel(product)))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> putProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto product
    ) {
        log.info("putProduct");
        return ResponseEntity.ok(
                productMapper.toResponse(productsService.update(id, productMapper.toModel(product)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(
            @PathVariable Long id
    ) {
        log.info("deleteProduct");
        productsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
