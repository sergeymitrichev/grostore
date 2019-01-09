CREATE TABLE public.ingredient
(
  id serial not null
    constraint ingredient_pkey
    primary key,
  forename varchar(100) not null
);
CREATE UNIQUE INDEX ingredient_forename_uindex
  ON public.ingredient (forename);

CREATE TABLE public.product_ingredient
(
  product_id    int NOT NULL,
  ingredient_id int NOT NULL,
  CONSTRAINT product_ingredient_pk PRIMARY KEY (product_id, ingredient_id),
  CONSTRAINT product_ingredient_product_id_fk FOREIGN KEY (product_id) REFERENCES public.product (id) ON DELETE CASCADE,
  CONSTRAINT product_ingredient_ingredient_id_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX product_ingredient_product_id_ingredient_id_uindex
  ON public.product_ingredient (product_id, ingredient_id)