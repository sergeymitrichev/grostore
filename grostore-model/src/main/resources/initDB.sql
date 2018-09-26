DROP TABLE IF EXISTS product_import_fields;
DROP TABLE IF EXISTS product_import;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS account;

CREATE TABLE product_import
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer,
    updated_by integer,
    name text NOT NULL,
    file text NOT NULL,
    type text,
    fields text,
    identity_field text,
    PRIMARY KEY (id)
)

DROP TABLE IF EXISTS product_import_fields;
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
    created_by integer,
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
    PRIMARY KEY (id)
)

CREATE TABLE category
(
    id serial NOT NULL,
    parent_id integer REFERENCES category(id) ON DELETE CASCADE default 1,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer,
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
INSERT INTO category (id, parent_id, name) VALUES (1, null, 'Root category');

CREATE TABLE product_category
(
    product_id int references product(id) on update cascade on delete cascade,
    category_id int references category(id) on update cascade on delete cascade,
    constraint product_category_pkey primary key (product_id, category_id)
)

CREATE TABLE price
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer,
    updated_by integer,
    product_id int references product(id) on update cascade on delete cascade,
    type text NOT NULL,
    value int NOT NULL,
    condition_value text,
    PRIMARY KEY (id)
);

CREATE TABLE account
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer,
    updated_by integer,
    name text NOT NULL,
    PRIMARY KEY (id)
)