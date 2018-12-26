create sequence hibernate_sequence
;

alter sequence hibernate_sequence owner to "user"
;

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
)
;

alter table flyway_schema_history owner to "user"
;

create index if not exists flyway_schema_history_s_idx
  on flyway_schema_history (success)
;

create table if not exists image
(
  entity_id integer not null,
  url varchar(255),
  alt varchar(255),
  title varchar(255),
  constraint image_entity_id_url_pk
  unique (entity_id, url)
)
;

alter table image owner to "user"
;

create table if not exists account
(
  id serial not null
    constraint account_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null,
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
)
;

alter table account owner to "user"
;

create table if not exists account_roles
(
  account_id integer not null
    constraint fktp61eta5i06bug3w1qr6286uf
    references account,
  role varchar(255)
)
;

alter table account_roles owner to "user"
;

create table if not exists category
(
  id serial not null
    constraint category_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null
    constraint category_unique_name_idx
    unique,
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
)
;

alter table category owner to "user"
;

create table if not exists category_image
(
  id serial not null
    constraint category_image_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  alt varchar(255),
  title varchar(255),
  url varchar(255) not null,
  created_by integer
    constraint fkbg69c37t877mc2pge5saf04n8
    references account,
  updated_by integer
    constraint fkofq1802tyhptaxt4otxfmjjyb
    references account,
  entity_id integer not null
    constraint fkfh3ory76p3beq5e4hhcuhelkc
    references category
)
;

alter table category_image owner to "user"
;

create table if not exists category_modification
(
  category_id integer not null
    constraint fk5j8jft4sbb8acme9nlot0vvo4
    references category,
  modification_id bigint not null
)
;

alter table category_modification owner to "user"
;

create table if not exists hibernate_sequences
(
  sequence_name varchar(255) not null
    constraint hibernate_sequences_pkey
    primary key,
  next_val bigint
)
;

alter table hibernate_sequences owner to "user"
;

create table if not exists modification
(
  id bigint not null
    constraint modification_pkey
    primary key,
  forename varchar(255)
)
;

alter table modification owner to "user"
;

create table if not exists modification_float
(
  id bigint not null
    constraint modification_float_pkey
    primary key,
  forename varchar(255)
    constraint modification_float_unique_forename_idx
    unique,
  unit varchar(56)
)
;

alter table modification_float owner to "user"
;

create table if not exists modification_float_value
(
  id bigint not null
    constraint modification_float_value_pkey
    primary key,
  modification_id bigint not null
    constraint fk_4gy0cybeof2qihtgax2i4so8h
    references modification_float,
  value real not null,
  values_id bigint not null
    constraint uk_q95v1phy6r4qar0exn8ua7wy
    unique
    constraint fkhtll9wgkvk8o3c7q8s0l5wu2m
    references modification_float_value
)
;

alter table modification_float_value owner to "user"
;

create table if not exists modification_string
(
  id bigint not null
    constraint modification_string_pkey
    primary key,
  forename varchar(255)
    constraint modification_string_unique_forename_idx
    unique
)
;

alter table modification_string owner to "user"
;

create table if not exists modification_string_value
(
  id bigint not null
    constraint modification_string_value_pkey
    primary key,
  modification_id bigint not null
    constraint fk_avt5bbeb9mf6e5luui3ijh7sr
    references modification_string,
  value varchar(255) not null,
  values_id bigint not null
    constraint uk_q42jb73td73s6pq5x2jm5nqgm
    unique
    constraint fk3mb40f0rtsxn0a1bqfxd3a3a7
    references modification_string_value
)
;

alter table modification_string_value owner to "user"
;

create table if not exists modification_value
(
  id bigint not null
    constraint modification_value_pkey
    primary key,
  modification_id bigint not null
)
;

alter table modification_value owner to "user"
;

create table if not exists price_rule
(
  id serial not null
    constraint price_rule_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null,
  created_by integer
    constraint fk4w572raepmat8nrb53c1asi0d
    references account,
  updated_by integer
    constraint fkt8b9o9q9vw99j8w5whgagdgf2
    references account
)
;

alter table price_rule owner to "user"
;

create table if not exists price_rule_row
(
  id serial not null
    constraint price_rule_row_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
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
  created_by integer
    constraint fkdyx52edv6f4upva42huyx31x1
    references account,
  updated_by integer
    constraint fkcltvwbwfbnaf8kwxxajjbmkih
    references account,
  price_rule_id integer not null
    constraint fkqnjvi4413rn39h8mboltq9xwc
    references price_rule
)
;

alter table price_rule_row owner to "user"
;

create table if not exists price_rule_types
(
  price_rule_id integer not null
    constraint fk60dk25eio7tuua5ju07rihtn5
    references price_rule,
  price_type varchar(255)
)
;

alter table price_rule_types owner to "user"
;

create table if not exists product
(
  id serial not null
    constraint product_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null,
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
)
;

alter table product owner to "user"
;

create table if not exists price
(
  product_id integer not null
    constraint fkk4mbgqf5yru5ib5b6w5l6ukkj
    references product,
  condition_value integer,
  type varchar(255),
  value integer
)
;

alter table price owner to "user"
;

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
)
;

alter table product_category owner to "user"
;

create table if not exists product_image
(
  id serial not null
    constraint product_image_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  alt varchar(255),
  title varchar(255),
  url varchar(255) not null,
  created_by integer
    constraint fkd1t5rs3kkn1n8nci06bap24t
    references account,
  updated_by integer
    constraint fkr1in40yhtu960n7hhsxpc7ktd
    references account,
  entity_id integer not null
    constraint fkigfe4isrjlbjt30u3kw3l75xt
    references product
)
;

alter table product_image owner to "user"
;

create table if not exists product_import
(
  id serial not null
    constraint product_import_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null,
  file varchar(255) not null,
  created_by integer
    constraint fkryqm2cxyuslkaxgf9nsjtt63d
    references account,
  updated_by integer
    constraint fkm1o0rwapelao8egwhd2qyvvk8
    references account,
  constraint product_import_unique_file_name_idx
  unique (file, forename)
)
;

alter table product_import owner to "user"
;

create table if not exists product_modification_value
(
  product_id integer not null
    constraint fkcs92ui6ddo1nb4hnn04oxd8ff
    references product,
  modification_value_id bigint not null
    constraint uk_6hfpx6k1gcavw9jdwvm54xiqr
    unique
)
;

alter table product_modification_value owner to "user"
;

create table if not exists product_import_fields
(
  product_import_id integer not null
    constraint fkq9vkivdwkk5dlbihjeqnuy6cm
    references product_import,
  fields integer
)
;

alter table product_import_fields owner to "user"
;

create table if not exists scheduled_task_config
(
  id serial not null
    constraint scheduled_task_config_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null
    constraint scheduled_task_config_unique_name_idx
    unique,
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
)
;

alter table scheduled_task_config owner to "user"
;

create table if not exists scheduled_task_config_url
(
  scheduled_task_config_id integer not null
    constraint fk6nt5owjoqidywuojy54wjlb5e
    references scheduled_task_config,
  category_id integer
    constraint fkaub819sl1g2lle1mqn67c8f4o
    references category,
  link varchar(255)
)
;

alter table scheduled_task_config_url owner to "user"
;

create table if not exists stock
(
  id serial not null
    constraint stock_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  enabled boolean default true,
  forename varchar(100) not null
    constraint stock_unique_name_idx
    unique,
  type integer default 0,
  created_by integer
    constraint fk5scb3k42hyhl3uk95ds4x9syi
    references account,
  updated_by integer
    constraint fkda480vgfwfs5v2vbhju8vjnkm
    references account
)
;

alter table stock owner to "user"
;

create table if not exists product_in_stock
(
  id serial not null
    constraint product_in_stock_pkey
    primary key,
  created timestamp default now() not null,
  updated timestamp,
  shelf varchar(16),
  created_by integer
    constraint fknpy6imv7lvf3gsutuao7xjey5
    references account,
  updated_by integer
    constraint fki2ig3rgo0u75ns9ser9m1xpdn
    references account,
  product_id integer
    constraint fkejxo2p1f8o365inshm72h84fq
    references product,
  stock_id integer
    constraint fkmyb4np9lg9bxa32rnj84b10ew
    references stock,
  constraint product_in_stock_unique_shelf_idx
  unique (stock_id, product_id, shelf)
)
;

alter table product_in_stock owner to "user"
;

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
)
;

alter table ucoz_categories_snapshot owner to "user"
;

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
)
;

alter table ucoz_products_snapshot owner to "user"
;