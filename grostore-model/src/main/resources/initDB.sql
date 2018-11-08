DROP TABLE IF EXISTS product_import_fields;
DROP TABLE IF EXISTS product_import;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS price;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS scheduled_task_config_url;
DROP TABLE IF EXISTS scheduled_task_config;

CREATE TABLE account
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer default 1,
    updated_by integer,
    name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    phone text,
    visited timestamp with time zone,
    PRIMARY KEY (id)
)

CREATE TABLE product_import
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer default 1,
    updated_by integer,
    name text NOT NULL,
    file text NOT NULL,
    type text,
    fields text,
    identity_field text,
    PRIMARY KEY (id)
)

CREATE TABLE product_import_fields
(
    id serial NOT NULL,
    fields text NOT NULL,
--     created timestamp with time zone default now() NOT NULL,
--     updated timestamp with time zone default now()  NOT NULL,
--     created_by integer,
--     updated_by integer,
--     type text NOT NULL,
--     column_number integer NOT NULL,
--     identity boolean default FALSE,
    product_import_id integer NOT NULL references product_import(id) ON DELETE CASCADE
--     PRIMARY KEY (id)
)

CREATE TABLE product
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer default 1,
    updated_by integer,
    sku text,
    unit text,
    name text NOT NULL,
    title text,
    url text,
    meta_description text,
    meta_keywords text,
    meta_image_index text,
    brief text,
    description text,
    PRIMARY KEY (id),
    constraint product_sku_uq unique (sku)
);

CREATE TABLE category
(
    id serial NOT NULL,
    parent_id integer REFERENCES category(id) ON DELETE CASCADE default 1,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer default 1,
    updated_by integer,
    name text NOT NULL,
    title text,
    url text,
    meta_description text,
    meta_keywords text,
    meta_image_index text,
    brief text,
    description text,
    PRIMARY KEY (id),
    UNIQUE(name)
);

CREATE TABLE product_category
(
    product_id int references product(id) on update cascade on delete cascade,
    category_id int references category(id) on update cascade on delete cascade,
    constraint product_category_pkey primary key (product_id, category_id)
);

CREATE TABLE price
(
    product_id int references product(id) on update cascade on delete cascade NOT NULL,
    type text NOT NULL,
    value int NOT NULL,
    condition_value text,
    constraint price_condition_uq unique (product_id, type, condition_value)
);

CREATE TABLE scheduled_task_config
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer default 1,
    updated_by integer,
    name text,
    type int,
    status int default 0,
    periodic boolean default FALSE,
    delay int default 0,
    PRIMARY KEY (id)
);

CREATE TABLE scheduled_task_config_url
(
    link text NOT NULL,
    category_id int default 1 references category(id) on update cascade on delete cascade NOT NULL,
    scheduled_task_config_id int references scheduled_task_config(id) on update cascade on delete cascade
);

CREATE TABLE ucoz_workers_snapshot
(
  id serial NOT NULL UNIQUE,
  created timestamp with time zone default now() NOT NULL
);

CREATE TABLE ucoz_categories_snapshot
(
  id int NOT NULL UNIQUE,
  parent_id int NOT NULL,
  cat_level int,
  name text,
  descr text,
  url text,
  num_entries int,
  cat_img_url text,
  worker_id int references ucoz_workers_snapshot(id) on update cascade on delete cascade
);

CREATE TABLE ucoz_products_snapshot
(
  id int NOT NULL UNIQUE,
  cat_id int NOT NULL references ucoz_categories_snapshot(id) on update cascade on delete cascade,
  sku text NOT NULL,
  price_in int DEFAULT 0,
  worker_id int references ucoz_workers_snapshot(id) on update cascade on delete cascade
);
