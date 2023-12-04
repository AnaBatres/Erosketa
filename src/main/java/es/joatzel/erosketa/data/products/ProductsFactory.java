package es.joatzel.erosketa.data.products;

import es.joatzel.erosketa.data.categories.CategoriesFactory;
import es.joatzel.erosketa.models.products.Product;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j

public class ProductsFactory {
    public static Map<Long, Product> getProductsDemoData() {
        log.info("Cargando datos de categories de demo");

        var categories = CategoriesFactory.getCategoriesDemoData();

        var map = new LinkedHashMap<Long, Product>();
        map.put(1L,
                new Product(1L, UUID.randomUUID(), "Name1",  "Description1", 12.54, 45, categories.get(1L),  LocalDateTime.now(), LocalDateTime.now(), false));
        map.put(2L,
                new Product(2L, UUID.randomUUID(), "Name2",  "Description2", 83.45, 34,  categories.get(2L),  LocalDateTime.now(), LocalDateTime.now(), false));
        map.put(3L,
                new Product(3L, UUID.randomUUID(), "Name3",  "Description3", 38.0, 4,  categories.get(2L),  LocalDateTime.now(), LocalDateTime.now(), false));
        map.put(4L,
                new Product(4L, UUID.randomUUID(), "Name4",  "Description4", 73.2, 33,  categories.get(3L),  LocalDateTime.now(), LocalDateTime.now(), false));

        return map;
    }
}
