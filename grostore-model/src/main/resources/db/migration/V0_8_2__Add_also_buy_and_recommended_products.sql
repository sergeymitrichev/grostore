CREATE TABLE product_recommended (
  parent_id int4 not null references product(id),
  child_id int4 not null references product(id),
  weight int4 not null default 1,
  constraint product_recommended_parent_id_child_id_uq unique (parent_id, child_id),
  constraint chk_no_self_relation check (parent_id != child_id)
);
CREATE TABLE product_also_buy (
  parent_id int4 not null references product(id),
  child_id int4 not null references product(id),
  weight int4 not null default 1,
  constraint product_also_buy_parent_id_child_id_uq unique (parent_id, child_id),
  constraint chk_no_self_relation check (parent_id != child_id)
);

INSERT INTO product (id, forename, sku)
VALUES (2,  'Product 2', 'P_SKU_2');

INSERT INTO product_recommended VALUES
       (1, 2, 1),
       (2, 1, 1);

INSERT INTO product_also_buy VALUES
       (1, 2, 1),
       (2, 1, 1);
