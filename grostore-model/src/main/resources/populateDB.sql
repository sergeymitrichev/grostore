INSERT INTO account (name, email, password) VALUES 
('root', 'root@root.root', 'rootroot'),
('robot', 'robot@robot.robot', 'rootroot');

INSERT INTO account_roles (role, account_id) VALUES
  ('ROLE_ADMIN', 1),
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2);

INSERT INTO category (id, parent_id, name) VALUES (1, null, 'Root category');