create table if not exists flyway_schema_history
(
  installed_rank integer not null
    constraint flyway_schema_history_pk
    primary key,
  version varchar(50),
  description varchar(200) not null,
  type varchar(20) not null,
  script varchar(1000) not null,
  checksum integer,
  installed_by varchar(100) not null,
  installed_on timestamp default now() not null,
  execution_time integer not null,
  success boolean not null
);

create index if not exists flyway_schema_history_s_idx
  on flyway_schema_history (success);

create table if not exists account
(
  id serial not null
    constraint account_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null,
  updated timestamp,
  email varchar(255) not null
    constraint account_unique_email_idx
    unique,
  password varchar(255) not null,
  phone varchar(255),
  visited timestamp,
  created_by integer
    constraint fkatwoepbie7ousm3au0cp7hira
    references account,
  updated_by integer
    constraint fkbbkf9by37bbwnh7vcj3q0qwt7
    references account
);

create table if not exists account_roles
(
  account_id integer not null
    constraint fktp61eta5i06bug3w1qr6286uf
    references account,
  role varchar(255)
);

create table if not exists category
(
  id serial not null
    constraint category_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null
    constraint category_unique_name_idx
    unique,
  updated timestamp,
  brief varchar(255),
  description varchar(4000),
  hgu varchar(255),
  meta_description varchar(255),
  meta_keywords varchar(255),
  meta_title varchar(100),
  created_by integer
    constraint fksj4smhmap5uqowsrv4i152rm9
    references account,
  updated_by integer
    constraint fktj02nr3rtixelbubvfgm9yqth
    references account,
  parent_id integer
    constraint fk2y94svpmqttx80mshyny85wqr
    references category
);

create table if not exists category_image
(
  id serial not null
    constraint category_image_pkey
    primary key,
  alt varchar(255),
  title varchar(255),
  url varchar(255) not null,
  entity_id integer not null
    constraint fkfh3ory76p3beq5e4hhcuhelkc
    references category
);

create table if not exists modification_float
(
  id serial not null
    constraint modification_float_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null
    constraint modification_float_unique_forename_idx
    unique,
  updated timestamp,
  unit varchar(56),
  created_by integer
    constraint fk27nupg10ug99w19jq9bya75m
    references account,
  updated_by integer
    constraint fkjdemowygavjvskwtxme37u61x
    references account
);

create table if not exists category_float_modification
(
  category_id integer not null
    constraint fk1sthcx52cq6he8vt8iaabqk8u
    references category,
  modification_float_id integer not null
    constraint fkjdjsb2o7gm1bi11ahei9j1fvs
    references modification_float
);

create table if not exists modification_float_value
(
  id serial not null
    constraint modification_float_value_pkey
    primary key,
  value real not null,
  modification_float_id integer not null
    constraint fk4ehqh2gd2m0pugmvf3g82r214
    references modification_float
);

create table if not exists modification_string
(
  id serial not null
    constraint modification_string_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null
    constraint modification_string_unique_forename_idx
    unique,
  updated timestamp,
  created_by integer
    constraint fknulbohiqadkq8rd4aabqr60y6
    references account,
  updated_by integer
    constraint fkgb6r61uf9xs4fxuxtj7rg53rn
    references account
);

create table if not exists category_modification
(
  modification_id integer not null
    constraint fkbqnmdt1e773e6qrsjgeaa6ob
    references modification_string,
  category_id integer not null
    constraint fk5j8jft4sbb8acme9nlot0vvo4
    references category
);

create table if not exists category_string_modification
(
  category_id integer not null
    constraint fkc6tqmsy728u2hjree2hdnui5b
    references category,
  modification_string_id integer not null
    constraint fkd4jhhs29gopixy2u5s6quibjc
    references modification_string
);

create table if not exists modification_string_value
(
  id serial not null
    constraint modification_string_value_pkey
    primary key,
  value varchar(255) not null,
  modification_string_id integer not null
    constraint fkjs796mg2lx7kcdn19oqhv5dip
    references modification_string
);

create table if not exists price_rule
(
  id serial not null
    constraint price_rule_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null,
  updated timestamp,
  created_by integer
    constraint fk4w572raepmat8nrb53c1asi0d
    references account,
  updated_by integer
    constraint fkt8b9o9q9vw99j8w5whgagdgf2
    references account
);

create table if not exists price_rule_row
(
  id serial not null
    constraint price_rule_row_pkey
    primary key,
  value_from bigint default 0 not null
    constraint price_rule_row_value_from_check
    check (value_from >= 0),
  value_to bigint default 0 not null
    constraint price_rule_row_value_to_check
    check (value_to >= 0),
  rule_type integer default 0,
  value bigint default 0 not null
    constraint price_rule_row_value_check
    check (value >= 0),
  price_rule_id integer not null
    constraint fkqnjvi4413rn39h8mboltq9xwc
    references price_rule
);

create table if not exists price_rule_types
(
  price_rule_id integer not null
    constraint fk60dk25eio7tuua5ju07rihtn5
    references price_rule,
  price_type varchar(255)
);

create table if not exists product
(
  id serial not null
    constraint product_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null,
  updated timestamp,
  brief varchar(255),
  description varchar(4000),
  hgu varchar(255),
  meta_description varchar(255),
  meta_keywords varchar(255),
  meta_title varchar(100),
  sku varchar(56) not null
    constraint product_unique_sku_idx
    unique,
  unit varchar(56),
  created_by integer
    constraint fkhotwugrl09e1r74ih4d34gk29
    references account,
  updated_by integer
    constraint fk443tm5659rp0qxbhkgxiaqi43
    references account
);

create table if not exists price
(
  product_id integer not null
    constraint fkk4mbgqf5yru5ib5b6w5l6ukkj
    references product,
  condition_value integer,
  type varchar(255),
  value integer
);

create table if not exists product_category
(
  category_id integer not null
    constraint fkkud35ls1d40wpjb5htpp14q4e
    references category,
  product_id integer not null
    constraint fk2k3smhbruedlcrvu6clued06x
    references product,
  constraint product_category_pkey
  primary key (product_id, category_id)
);

create table if not exists product_image
(
  id serial not null
    constraint product_image_pkey
    primary key,
  alt varchar(255),
  title varchar(255),
  url varchar(255) not null,
  entity_id integer not null
    constraint fkigfe4isrjlbjt30u3kw3l75xt
    references product
);

create table if not exists product_import
(
  id serial not null
    constraint product_import_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null,
  updated timestamp,
  file varchar(255) not null,
  created_by integer
    constraint fkryqm2cxyuslkaxgf9nsjtt63d
    references account,
  updated_by integer
    constraint fkm1o0rwapelao8egwhd2qyvvk8
    references account,
  constraint product_import_unique_file_name_idx
  unique (file, forename)
);

create table if not exists product_modification_float_value
(
  product_id integer not null
    constraint fkq8o438jsx25lg5dp6rcx7k0bh
    references product,
  modification_float_value_id integer not null
    constraint uk_mbhi22jkr8uroqb6ckax5ton5
    unique
    constraint fks26bt2o67kgrcml2926m3tjl4
    references modification_float_value
);

create table if not exists product_modification_string_value
(
  product_id integer not null
    constraint fkhpk43n6102bdbi1ldb88ptx5o
    references product,
  modification_string_value_id integer not null
    constraint uk_lgjh3e94sjxp7kolekwxks12n
    unique
    constraint fko2d29huf1s4r1kdoldxj18c4c
    references modification_string_value
);

create table if not exists product_import_fields
(
  product_import_id integer not null
    constraint fkq9vkivdwkk5dlbihjeqnuy6cm
    references product_import,
  fields integer
);

create table if not exists scheduled_task_config
(
  id serial not null
    constraint scheduled_task_config_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null
    constraint scheduled_task_config_unique_name_idx
    unique,
  updated timestamp,
  delay bigint default 0,
  periodic boolean default false,
  status integer default 0,
  type integer not null,
  created_by integer
    constraint fk3354x2vtkhmk9mi9andte6qnp
    references account,
  updated_by integer
    constraint fk99phtvn8q646vyt1lcmsc2jl9
    references account,
  account_id integer
    constraint fk5vds1gyyw10j022gqptb602j4
    references account
);

create table if not exists scheduled_task_config_url
(
  scheduled_task_config_id integer not null
    constraint fk6nt5owjoqidywuojy54wjlb5e
    references scheduled_task_config,
  category_id integer
    constraint fkaub819sl1g2lle1mqn67c8f4o
    references category,
  link varchar(255)
);

create table if not exists stock
(
  id serial not null
    constraint stock_pkey
    primary key,
  created timestamp default now() not null,
  enabled boolean default true,
  entity_order integer default 0,
  forename varchar(100) not null
    constraint stock_unique_name_idx
    unique,
  updated timestamp,
  type integer default 0,
  created_by integer
    constraint fk5scb3k42hyhl3uk95ds4x9syi
    references account,
  updated_by integer
    constraint fkda480vgfwfs5v2vbhju8vjnkm
    references account
);

create table if not exists product_in_stock
(
  id serial not null
    constraint product_in_stock_pkey
    primary key,
  shelf varchar(16),
  product_id integer
    constraint fkejxo2p1f8o365inshm72h84fq
    references product,
  stock_id integer
    constraint fkmyb4np9lg9bxa32rnj84b10ew
    references stock,
  constraint product_in_stock_unique_shelf_idx
  unique (stock_id, product_id, shelf)
);

create table if not exists ucoz_categories_snapshot
(
  id integer not null
    constraint ucoz_categories_snapshot_pkey
    primary key,
  descr varchar(255),
  cat_img_url varchar(255),
  cat_level integer,
  forename varchar(255),
  parent_id integer,
  num_entries integer,
  url varchar(255)
);

create table if not exists ucoz_products_snapshot
(
  id integer not null
    constraint ucoz_products_snapshot_pkey
    primary key,
  hide boolean,
  forename varchar(255),
  price_in integer,
  sku varchar(255),
  stock varchar(255),
  cat_id integer
    constraint fkahkhqlnv0ssp11hcceqihl6ih
    references ucoz_categories_snapshot
);

