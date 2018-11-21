create table account (
       id  serial not null,
        created timestamp default now() not null,
        updated timestamp,
        forename varchar(100) not null,
        email varchar(255) not null,
        password varchar(255) not null,
        phone varchar(255),
        visited timestamp,
        created_by int4,
        updated_by int4,
        primary key (id)
    );
 
    
    create table account_roles (
       account_id int4 not null,
        role varchar(255)
    );
 
    
    create table category (
       id  serial not null,
        created timestamp default now() not null,
        updated timestamp,
        forename varchar(100) not null,
        brief varchar(255),
        description varchar(4000),
        meta_description varchar(256),
        meta_image_index int4,
        meta_keywords varchar(256),
        title varchar(255),
        url varchar(255),
        created_by int4,
        updated_by int4,
        parent_id int4,
        primary key (id)
    );
 
    
    create table price (
       product_id int4 not null,
        condition_value int4,
        type varchar(255),
        value int4
    );
 
    
    create table product (
       id  serial not null,
        created timestamp default now() not null,
        updated timestamp,
        forename varchar(100) not null,
        brief varchar(255),
        description varchar(4000),
        meta_description varchar(256),
        meta_image_index int4,
        meta_keywords varchar(256),
        title varchar(255),
        url varchar(255),
        sku varchar(56) not null,
        unit varchar(56),
        created_by int4,
        updated_by int4,
        primary key (id)
    );
 
    
    create table product_category (
       category_id int4 not null,
        product_id int4 not null
    );
 
    
    create table product_import (
       id  serial not null,
        created timestamp default now() not null,
        updated timestamp,
        forename varchar(100) not null,
        file varchar(255) not null,
        created_by int4,
        updated_by int4,
        primary key (id)
    );
 
    
    create table product_import_fields (
       product_import_id int4 not null,
        fields int4
    );
 
    
    create table scheduled_task_config (
       id  serial not null,
        created timestamp default now() not null,
        updated timestamp,
        forename varchar(100) not null,
        delay bigint default 0,
        periodic int default 0,
        status int default 0,
        type int4 not null,
        created_by int4,
        updated_by int4,
        primary key (id)
    );
 
    
    create table scheduled_task_config_url (
       scheduled_task_config_id int4 not null,
        category_id int4,
        link varchar(255)
    );
 
    
    create table ucoz_categories_snapshot (
       id int4 not null,
        descr varchar(255),
        cat_img_url varchar(255),
        cat_level int4,
        forename varchar(255),
        parent_id int4,
        num_entries int4,
        url varchar(255),
        primary key (id)
    );
 
    
    create table ucoz_products_snapshot (
       id int4 not null,
        hide boolean,
        forename varchar(255),
        price_in int4,
        sku varchar(255),
        stock varchar(255),
        cat_id int4,
        primary key (id)
    );
 
    
    alter table if exists account 
       add constraint account_unique_email_idx unique (email);
 
    
    alter table if exists category 
       add constraint category_unique_name_idx unique (forename);
 
    
    alter table if exists product 
       add constraint product_unique_sku_idx unique (sku);
 
    
    alter table if exists product_import 
       add constraint product_import_unique_file_name_idx unique (file, forename);
 
    
    alter table if exists scheduled_task_config 
       add constraint scheduled_task_config_unique_name_idx unique (forename);
 
    
    alter table if exists account 
       add constraint FKatwoepbie7ousm3au0cp7hira 
       foreign key (created_by) 
       references account;
 
    
    alter table if exists account 
       add constraint FKbbkf9by37bbwnh7vcj3q0qwt7 
       foreign key (updated_by) 
       references account;
 
    
    alter table if exists account_roles 
       add constraint FKtp61eta5i06bug3w1qr6286uf 
       foreign key (account_id) 
       references account;
 
    
    alter table if exists category 
       add constraint FKsj4smhmap5uqowsrv4i152rm9 
       foreign key (created_by) 
       references account;
 
    
    alter table if exists category 
       add constraint FKtj02nr3rtixelbubvfgm9yqth 
       foreign key (updated_by) 
       references account;
 
    
    alter table if exists category 
       add constraint FK2y94svpmqttx80mshyny85wqr 
       foreign key (parent_id) 
       references category;
 
    
    alter table if exists price 
       add constraint FKk4mbgqf5yru5ib5b6w5l6ukkj 
       foreign key (product_id) 
       references product;
 
    
    alter table if exists product 
       add constraint FKhotwugrl09e1r74ih4d34gk29 
       foreign key (created_by) 
       references account;
 
    
    alter table if exists product 
       add constraint FK443tm5659rp0qxbhkgxiaqi43 
       foreign key (updated_by) 
       references account;
 
    
    alter table if exists product_category 
       add constraint FK2k3smhbruedlcrvu6clued06x 
       foreign key (product_id) 
       references product;
 
    
    alter table if exists product_category 
       add constraint FKkud35ls1d40wpjb5htpp14q4e 
       foreign key (category_id) 
       references category;
 
    
    alter table if exists product_import 
       add constraint FKryqm2cxyuslkaxgf9nsjtt63d 
       foreign key (created_by) 
       references account;
 
    
    alter table if exists product_import 
       add constraint FKm1o0rwapelao8egwhd2qyvvk8 
       foreign key (updated_by) 
       references account;
 
    
    alter table if exists product_import_fields 
       add constraint FKq9vkivdwkk5dlbihjeqnuy6cm 
       foreign key (product_import_id) 
       references product_import;
 
    
    alter table if exists scheduled_task_config 
       add constraint FK3354x2vtkhmk9mi9andte6qnp 
       foreign key (created_by) 
       references account;
 
    
    alter table if exists scheduled_task_config 
       add constraint FK99phtvn8q646vyt1lcmsc2jl9 
       foreign key (updated_by) 
       references account;
 
    
    alter table if exists scheduled_task_config_url 
       add constraint FKaub819sl1g2lle1mqn67c8f4o 
       foreign key (category_id) 
       references category;
 
    
    alter table if exists scheduled_task_config_url 
       add constraint FK6nt5owjoqidywuojy54wjlb5e 
       foreign key (scheduled_task_config_id) 
       references scheduled_task_config;
 
    
    alter table if exists ucoz_products_snapshot 
       add constraint FKahkhqlnv0ssp11hcceqihl6ih 
       foreign key (cat_id) 
       references ucoz_categories_snapshot;