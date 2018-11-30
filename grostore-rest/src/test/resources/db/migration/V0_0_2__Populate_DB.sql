INSERT INTO account (forename, email, password) VALUES
('root', 'root@root.root', '$2a$10$QDp3bYujXZW49X.vPv8lmO2lMdOV.SO4t5FlSdhX43PB69XMP10.q');

INSERT INTO account_roles (role, account_id) VALUES
  ('ROLE_ADMIN', 1),
  ('ROLE_USER', 1);