BEGIN TRANSACTION;

CREATE TABLE voucher (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  discount DECIMAL NOT NULL,
  quantity INT NOT NULL,
  expiration_date TIMESTAMP NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL
);

ALTER TABLE check_out_ticket
ADD COLUMN voucher_id VARCHAR(10);

INSERT INTO voucher (id, discount, quantity, expiration_date, created_by, created_date, updated_by, updated_date, mark_for_delete)
VALUES
  ('V_1000', 1000.0, 100, '2023-12-31 23:59:59', 'ADMIN', '2023-06-20 12:00:00', 'ADMIN', '2023-06-20 12:00:00', false),
  ('V_2000', 2000.0, 50, '2023-12-31 23:59:59', 'ADMIN', '2023-06-20 12:00:00', 'ADMIN', '2023-06-20 12:00:00', false),
  ('V_3000', 3000.0, 200, '2023-12-31 23:59:59', 'ADMIN', '2023-06-20 12:00:00', 'ADMIN', '2023-06-20 12:00:00', false),
  ('V_4000', 4000.0, 75, '2023-12-31 23:59:59', 'ADMIN', '2023-06-20 12:00:00', 'ADMIN', '2023-06-20 12:00:00', false),
  ('V_5000', 5000.0, 150, '2023-12-31 23:59:59', 'ADMIN', '2023-06-20 12:00:00', 'ADMIN', '2023-06-20 12:00:00', false),
  ('V_EXPIRED', 6000.0, 150, '2000-01-01 23:59:59', 'ADMIN', '2023-06-20 12:00:00', 'ADMIN', '2023-06-20 12:00:00', false);

COMMIT;