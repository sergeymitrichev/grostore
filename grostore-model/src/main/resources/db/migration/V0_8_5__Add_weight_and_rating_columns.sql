ALTER TABLE product
  ADD COLUMN weight integer DEFAULT 0,
  ADD COLUMN rating_sum integer DEFAULT 0,
  ADD COLUMN rating_average integer DEFAULT 0,
  ADD COLUMN rating_count integer DEFAULT 0;

ALTER TABLE category
  ADD COLUMN rating_sum integer DEFAULT 0,
  ADD COLUMN rating_average integer DEFAULT 0,
  ADD COLUMN rating_count integer DEFAULT 0;

ALTER TABLE brand
  ADD COLUMN rating_sum integer DEFAULT 0,
  ADD COLUMN rating_average integer DEFAULT 0,
  ADD COLUMN rating_count integer DEFAULT 0;