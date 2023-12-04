-- RAQUETAS
-- Creo una secuencia para el id de las categories,
-- si te das cuenta es lo que hace el script, pero como estoy metiendo datos
-- en la tabla, necesito que el id sea autoincremental, a partir de un valor
DROP SEQUENCE IF EXISTS categories_seq;
CREATE SEQUENCE categories_seq START WITH 100 INCREMENT BY 1;

-- Nuevos Inserts
INSERT INTO categories (id, uuid, name, description, color, created_at, updated_at, deleted)
VALUES (1, 'b3f6c4a4-37f1-4a1d-8e9d-6da7e652a367', 'Category1', 'Description1', 'blue', NOW(), NOW(), false);

INSERT INTO categories (id, uuid, name, description, color, created_at, updated_at, deleted)
VALUES (2, 'c4d60db7-25f2-4c8e-8341-3b6c52a4f3cc', 'Category2', 'Description2', 'white', NOW(), NOW(), false);

INSERT INTO categories (id, uuid, name, description, color, created_at, updated_at, deleted)
VALUES (3, '9f8d99bb-23cc-48b4-ae65-53cbf4973ea0', 'Category3', 'Description3', 'yellow', NOW(), NOW(), false);

--
DROP SEQUENCE IF EXISTS products_seq;
create SEQUENCE products_seq START WITH 100 INCREMENT BY 1;

INSERT INTO products (id, uuid, name, description, price, stock, category_id, created_at, updated_at, deleted)
    VALUES (1, '123e4567-e89b-12d3-a456-426614174001', 'Producto1', 'Description1',
            29.99, 10, 1, NOW(), NOW(), false);

INSERT INTO products (id, uuid, name, description, price, stock, category_id, created_at, updated_at, deleted)
VALUES (2, '223e4567-e89b-12d3-a456-426614174002', 'Producto2', 'Description2',
        39.99, 5, 2, NOW(), NOW(), false);

INSERT INTO products (id, uuid, name, description, price, stock, category_id, created_at, updated_at, deleted)
    VALUES (3,'323e4567-e89b-12d3-a456-426614174003', 'Producto3', 'Description3',
            19.99, 15, 1, NOW(), NOW(), false);

