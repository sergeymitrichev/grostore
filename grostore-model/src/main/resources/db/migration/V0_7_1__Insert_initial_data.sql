INSERT INTO account (id, forename, email, password)
VALUES (1, 'admin', 'sergeymitrichev@gmail.com', '$2a$10$QDp3bYujXZW49X.vPv8lmO2lMdOV.SO4t5FlSdhX43PB69XMP10.q');

INSERT INTO account_roles (role, account_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_USER', 1);

INSERT INTO category (id, forename, description, enabled)
VALUES (1, 'Scheduler results', 'Category for temporary storing products before manager check them', false);

INSERT INTO product (id, forename, sku)
VALUES (1, 'Product', 'P_SKU');

INSERT INTO product_category (category_id, product_id) VALUES (1, 1);

INSERT INTO price_rule (id, forename) VALUES (1, 'Default matrix');

INSERT INTO price_rule_row (value_from, value_to, value, rule_type, price_rule_id)
VALUES (0, 50, 5, 0, 1),
       (50, 500, 15, 1, 1),
       (500, 5000, 10, 1, 1);

INSERT INTO price_rule_types (price_rule_id, price_type)
values (1, 'PRICE_TYPE_IN'),
       (1, 'PRICE_TYPE_WHOLESALE_IN');

INSERT INTO modification_float (id, forename, unit)
values  (1, 'Срок годности', 'дн.');

INSERT INTO modification_string (id, forename)
values  (1, 'Диетический стол №5');

INSERT INTO modification_string (id, forename)
values  (2, 'Диетический стол №9');

INSERT INTO modification_float_value (id, value, modification_float_id)
values (1, 5, 1);

INSERT INTO modification_string_value (id, value, modification_string_id)
values (1, '', 1);

INSERT INTO modification_string_value (id, value, modification_string_id)
values (2, '', 2);

INSERT INTO category_float_modification (category_id, modification_float_id)
VALUES (1, 1);
INSERT INTO product_modification_float_value (product_id, modification_float_value_id)
VALUES (1, 1);

INSERT INTO category_string_modification (category_id, modification_string_id)
VALUES (1, 1);
INSERT INTO category_string_modification (category_id, modification_string_id)
VALUES (1, 2);
INSERT INTO product_modification_string_value (product_id, modification_string_value_id)
VALUES (1, 1);
INSERT INTO product_modification_string_value (product_id, modification_string_value_id)
VALUES (1, 2);