create table if not exists brand_image
(
  id serial not null
    constraint brand_image_pkey
    primary key,
  alt varchar(255),
  title varchar(255),
  url varchar(255) not null,
  entity_id integer not null
    constraint fk_brand_entity_id
    references brand
);