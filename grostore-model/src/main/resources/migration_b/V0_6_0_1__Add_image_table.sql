create table image (
    entity_id int4 not null,
    url varchar(255),
    alt varchar(255),
    title varchar(255)
);
ALTER TABLE image
 ADD CONSTRAINT image_entity_id_url_pk UNIQUE (entity_id, url);