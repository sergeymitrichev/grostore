CREATE TABLE brand (
  id serial not null
    constraint brand_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null
    constraint brand_unique_name_idx
    unique,
  updated timestamp,
  brief varchar(255),
  description varchar(4000),
  hgu varchar(255),
  meta_description varchar(255),
  meta_keywords varchar(255),
  meta_title varchar(100),
  country varchar(255),
  city varchar(255),
  created_by integer
    constraint fk_brand_created_by_account
    references account,
  updated_by integer
    constraint fk_brand_updated_by_account
    references account
);

ALTER TABLE product
  ADD COLUMN brand_id integer references brand(id)