DROP TABLE IF EXISTS
    price_rule_types,
    price_rule_row,
    price_rule;

create table price_rule (
  id serial not null,
  created timestamp default now() not null,
  updated timestamp,
  forename varchar(100) not null,
  enabled boolean default true,
  created_by int4 references account(id),
  updated_by int4 references account(id),
  primary key (id)
);

create table price_rule_types (
  price_rule_id int4 not null references price_rule(id),
  price_type varchar(255)
);

create table price_rule_row (
  id serial not null,
  created timestamp default now() not null,
  updated timestamp,
  value_from bigint default 0,
  value_to bigint default 0,
  rule_type int default 0,
  value bigint default 0,
  price_rule_id int4 references price_rule(id),
  created_by int4 references account(id),
  updated_by int4 references account(id),
  primary key (id)
);

INSERT INTO price_rule (forename) VALUES ('Default matrix');

INSERT INTO price_rule_row (value_from, value_to, value, rule_type, price_rule_id)
VALUES
    (0, 50, 5, 0, 1),
    (50, 500, 15, 1, 1),
    (500, 5000, 10, 1, 1);

INSERT INTO price_rule_types (price_rule_id, price_type) values
    (1, 'PRICE_TYPE_IN'),
    (1, 'PRICE_TYPE_WHOLESALE_IN')