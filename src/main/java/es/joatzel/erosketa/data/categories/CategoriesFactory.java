package es.joatzel.erosketa.data.categories;

import es.joatzel.erosketa.models.categories.Category;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class CategoriesFactory {

    public static Map<Long, Category> getCategoriesDemoData() {
        log.info("Cargando datos de categories de demo");
        var map = new LinkedHashMap<Long, Category>();
        map.put(1L,
                new Category(1L, UUID.randomUUID(), "Bebidas", "Hydro Surge", "green", LocalDateTime.now(), LocalDateTime.now(), false));
        map.put(2L,
                new Category(2L, UUID.randomUUID(), "Cuidado Personal", "Glide Pro", "white", LocalDateTime.now(), LocalDateTime.now(), false));
        map.put(3L,
                new Category(3L, UUID.randomUUID(), "Productos de limpieza", "Clean Sweep", "yellow", LocalDateTime.now(), LocalDateTime.now(), false));

        return map;
    }
}
