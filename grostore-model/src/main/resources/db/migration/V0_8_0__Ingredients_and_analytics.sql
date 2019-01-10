CREATE TABLE IF NOT EXISTS ingredient
(
  id serial not null
    constraint ingredient_pkey
    primary key,
  forename varchar(100) not null
);
CREATE UNIQUE INDEX ingredient_forename_uindex
  ON public.ingredient (forename);

CREATE TABLE IF NOT EXISTS product_ingredient
(
  product_id    int NOT NULL,
  ingredient_id int NOT NULL,
  CONSTRAINT product_ingredient_pk PRIMARY KEY (product_id, ingredient_id),
  CONSTRAINT product_ingredient_product_id_fk FOREIGN KEY (product_id) REFERENCES public.product (id) ON DELETE CASCADE,
  CONSTRAINT product_ingredient_ingredient_id_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX product_ingredient_product_id_ingredient_id_uindex
  ON public.product_ingredient (product_id, ingredient_id);

CREATE TABLE IF NOT EXISTS product_analytic
(
  id int NOT NULL CONSTRAINT product_analytic_pkey PRIMARY KEY REFERENCES product(id),
  add_to_cart bigint default 0,
  add_to_cart_quantity bigint default 0,
  sales bigint default 0,
  sales_quantity bigint default 0,
  deliveries bigint default 0,
  deliveries_quantity bigint default 0,
  out_of_stocks bigint default 0,
  price_inc bigint default 0,
  price_dec bigint default 0,
  views bigint default 0
);

CREATE TABLE IF NOT EXISTS product_analytic_by_origin
(
  id SERIAL NOT NULL
    CONSTRAINT product_analytic_by_origin_pkey
    PRIMARY KEY,
  origin varchar(16) NOT NULL,
  add_to_cart bigint default 0,
  add_to_cart_quantity bigint default 0,
  sales bigint default 0,
  sales_quantity bigint default 0,
  deliveries bigint default 0,
  deliveries_quantity bigint default 0,
  out_of_stocks bigint default 0,
  price_inc bigint default 0,
  price_dec bigint default 0,
  views bigint default 0
);