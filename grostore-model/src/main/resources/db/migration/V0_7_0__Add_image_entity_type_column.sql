--TODO delete before release
DROP TABLE IF EXISTS image;
-- TRUNCATE public.image;
-- DELETE FROM public.image;
-- ALTER TABLE public.image DROP COLUMN IF EXISTS entity_type;
--
create table product_image (
  id serial not null,
  created timestamp default now() not null,
  updated timestamp,
  created_by int4,
  updated_by int4,
  primary key (id),

  entity_id int4 not null references product(id),
  url varchar(255),
  alt varchar(255),
  title varchar(255)
);

create table category_image (
  id serial not null,
  created timestamp default now() not null,
  updated timestamp,
  created_by int4,
  updated_by int4,
  primary key (id),

  entity_id int4 not null references category(id),
  url varchar(255),
  alt varchar(255),
  title varchar(255)
);