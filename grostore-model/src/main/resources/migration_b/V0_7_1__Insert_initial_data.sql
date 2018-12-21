INSERT INTO account (id, forename, email, password)
VALUES (1, 'admin', 'sergeymitrichev@gmail.com', '$2a$10$QDp3bYujXZW49X.vPv8lmO2lMdOV.SO4t5FlSdhX43PB69XMP10.q');

INSERT INTO account_roles (role, account_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_USER', 1);

INSERT INTO category (forename, description, enabled)
VALUES ('Scheduler results', 'Category for temporary storing products before manager check them', false);

INSERT INTO price_rule (id, forename) VALUES (1, 'Default matrix');

INSERT INTO price_rule_row (value_from, value_to, value, rule_type, price_rule_id)
VALUES (0, 50, 5, 0, 1),
       (50, 500, 15, 1, 1),
       (500, 5000, 10, 1, 1);

INSERT INTO price_rule_types (price_rule_id, price_type)
values (1, 'PRICE_TYPE_IN'),
       (1, 'PRICE_TYPE_WHOLESALE_IN');