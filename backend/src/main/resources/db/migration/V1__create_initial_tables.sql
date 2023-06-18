BEGIN TRANSACTION;

CREATE TABLE member (
  id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  plate_number VARCHAR(10) NOT NULL,
  member_expired_date TIMESTAMP NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL
);

CREATE TABLE address (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  street VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  postal_code VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL
);

CREATE TABLE company (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE vehicle_type (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL
);

CREATE TABLE parking_lot (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  vehicle_capacity INTEGER NOT NULL,
  address_id VARCHAR(36) NOT NULL,
  company_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (address_id) REFERENCES address (id),
  FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE parking_lot_vehicle_type (
  id VARCHAR(36) PRIMARY KEY,
  parking_lot_id VARCHAR(36) NOT NULL,
  vehicle_type_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (parking_lot_id) REFERENCES parking_lot (id),
  FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type (id)
);

CREATE TABLE parking_rate (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  rate_per_hour DECIMAL NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL
);

CREATE TABLE parking_spot (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  area VARCHAR(5) NOT NULL,
  code VARCHAR(5) NOT NULL,
  vehicle_type_id VARCHAR(20) NOT NULL,
  is_occupied BOOLEAN NOT NULL,
  parking_lot_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type (id),
  FOREIGN KEY (parking_lot_id) REFERENCES parking_lot (id)
);

CREATE TABLE payment_method (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  name VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL
);

CREATE TABLE parking_lot_payment_method (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  parking_lot_id VARCHAR(36) NOT NULL,
  payment_method_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (parking_lot_id) REFERENCES parking_lot (id),
  FOREIGN KEY (payment_method_id) REFERENCES payment_method (id)
);

CREATE TABLE officer (
  id VARCHAR(8) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  parking_lot_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (parking_lot_id) REFERENCES parking_lot (id)
);

CREATE TABLE check_in_ticket (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  plate_number VARCHAR(10) NOT NULL,
  member_id VARCHAR(255),
  parking_spot_id VARCHAR(36) NOT NULL,
  officer_id VARCHAR(36) NOT NULL,
  is_active BOOLEAN NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (parking_spot_id) REFERENCES parking_spot (id),
  FOREIGN KEY (officer_id) REFERENCES officer (id)
);

CREATE TABLE check_out_ticket (
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  price DECIMAL NOT NULL,
  discount DECIMAL NOT NULL,
  final_price DECIMAL NOT NULL,
  officer_id VARCHAR(36) NOT NULL,
  check_in_ticket_id VARCHAR(36) NOT NULL,
  payment_method_id VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  mark_for_delete BOOLEAN NOT NULL,
  FOREIGN KEY (officer_id) REFERENCES officer (id),
  FOREIGN KEY (check_in_ticket_id) REFERENCES check_in_ticket (id)
);

COMMIT;
