DROP TABLE IF EXISTS product_imports_fields;
DROP TABLE IF EXISTS product_imports;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE product_imports
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

CREATE TABLE product_imports_fields
(
    id serial NOT NULL,
    created timestamp with time zone default now() NOT NULL,
    updated timestamp with time zone default now()  NOT NULL,
    created_by integer,
    updated_by integer,
    type text NOT NULL,
    column_number integer NOT NULL,
    identity boolean default FALSE,
    product_import_id integer NOT NULL references product_imports(id),
    PRIMARY KEY (id)
)

CREATE TABLE products
(
    id serial NOT NULL,
    created timestamp with time zone NOT NULL,
    update timestamp with time zone NOT NULL,
    created_by integer NOT NULL,
    updated_by integer NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
)

CREATE TABLE users
(
    id serial NOT NULL,
    created timestamp with time zone NOT NULL,
    update timestamp with time zone NOT NULL,
    created_by integer NOT NULL,
    updated_by integer NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
)