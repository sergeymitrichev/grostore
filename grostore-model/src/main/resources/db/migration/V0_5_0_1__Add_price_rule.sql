create table price_rule (
  id serial not null,
  created timestamp default now() not null,
  updated timestamp,
  type int default 0,
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
  created_by int4 references account(id),
  updated_by int4 references account(id),
  primary key (id)
)