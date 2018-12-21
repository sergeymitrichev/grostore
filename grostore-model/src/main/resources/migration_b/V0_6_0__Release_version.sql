ALTER TABLE public.product DROP meta_image_index;

--ALTER TABLE public.product DROP url;
--ALTER TABLE public.product ADD hgu varchar(255) NULL;
ALTER TABLE public.product RENAME COLUMN url TO hgu;
CREATE UNIQUE INDEX product_hgu_uindex ON public.product (hgu);

--ALTER TABLE public.product ADD meta_title varchar(100) NULL;
--ALTER TABLE public.product ADD meta_description varchar(255) NULL;
ALTER TABLE public.product RENAME COLUMN title TO meta_title;

ALTER TABLE public.product ADD meta_image_url varchar(255) NULL;
ALTER TABLE public.product ADD meta_image_alt varchar(255) NULL;
ALTER TABLE public.product ADD meta_image_title varchar(255) NULL;

--ALTER TABLE public.product ADD meta_keywords varchar(255) NULL;
--ALTER TABLE public.product ADD brief varchar(255) NULL;
--ALTER TABLE public.product ADD description varchar(4000) NULL;

