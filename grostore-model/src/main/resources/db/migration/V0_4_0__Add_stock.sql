create table stock (
    id serial not null,
    created timestamp default now() not null,
    updated timestamp,
    forename varchar(100) not null,
    type int default 0,
    created_by int4,
    updated_by int4,
    primary key (id)
);

create table product_in_stock (
    id serial not null,
    created timestamp default now() not null,
    updated timestamp,
    stock_id int4,
    product_id int4,
    shelf varchar(16),
    created_by int4,
    updated_by int4,
    primary key (id)
);

alter table if exists stock add constraint stock_unique_name_idx unique (forename);
alter table if exists product_in_stock add constraint product_in_stock_unique_shelf_idx unique (stock_id, product_id, shelf);