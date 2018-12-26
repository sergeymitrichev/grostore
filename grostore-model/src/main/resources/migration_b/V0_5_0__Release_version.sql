ALTER TABLE public.scheduled_task_config DROP periodic;

ALTER TABLE scheduled_task_config
  ADD COLUMN periodic BOOLEAN DEFAULT FALSE;

ALTER TABLE public.scheduled_task_config ADD account_id int NOT NULL;
ALTER TABLE public.scheduled_task_config
ADD CONSTRAINT scheduled_task_config_account_id_fk
FOREIGN KEY (account_id) REFERENCES public.account (id);

--add enabled column for all entitites
ALTER TABLE product
  ADD COLUMN enabled BOOLEAN DEFAULT TRUE;

ALTER TABLE category
  ADD COLUMN enabled BOOLEAN DEFAULT TRUE;

ALTER TABLE account
  ADD COLUMN enabled BOOLEAN DEFAULT TRUE;

  ALTER TABLE scheduled_task_config
  ADD COLUMN enabled BOOLEAN DEFAULT TRUE;

--create scheduler default category for new products
INSERT INTO category (
    forename,
    description,
    enabled
    )
VALUES (
    'Schduler results',
    'Category for temporary storing products before manager check them',
    false
)
ON CONFLICT (id) DO NOTHING;

ALTER TABLE public.product_category
 ADD CONSTRAINT product_category_pk UNIQUE (category_id, product_id);