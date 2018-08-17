DROP TABLE IF EXISTS product_import_fields;
DROP TABLE IF EXISTS product_import;
DROP TABLE IF EXISTS product;
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

CREATE TABLE product_import_field
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer,
    updated_by integer,
    type text NOT NULL,
    column_number integer NOT NULL,
    identity boolean default FALSE,
    product_import_id integer NOT NULL references product_import(id),
    PRIMARY KEY (id)
)

CREATE TABLE product
(
    id serial NOT NULL,
    created timestamp with time zone NOT NULL,
    update timestamp with time zone NOT NULL,
    created_by integer NOT NULL,
    updated_by integer NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
)

CREATE TABLE account
(
    id serial NOT NULL,
    created timestamp with time zone NOT NULL,
    update timestamp with time zone NOT NULL,
    created_by integer NOT NULL,
    updated_by integer NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
)