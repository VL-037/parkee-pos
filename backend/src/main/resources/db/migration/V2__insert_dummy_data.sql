INSERT INTO member (id, name, plate_number, member_expired_date, created_by, created_date, updated_by, updated_date, mark_for_delete)
VALUES
  ('c4a760a8-dbcf-4d06-8d4c-536b91788053', 'John Doe', 'AB 1234 CD', '2023-06-30 00:00:00', 'ADMIN', '2023-06-16 10:00:00', 'ADMIN', '2023-06-16 10:00:00', FALSE),
  ('51f4d9cd-3c14-48e5-91c9-45d89fdd0b16', 'Jane Smith', 'XY 9876 ZA', '2023-07-15 00:00:00', 'ADMIN', '2023-06-16 10:00:00', 'ADMIN', '2023-06-16 10:00:00', FALSE),
  ('e2fc084a-f9db-4d71-9e88-c0e62a328e19', 'Mike Johnson', 'PQ 4567 RS', '2023-08-01 00:00:00', 'ADMIN', '2023-06-16 10:00:00', 'ADMIN', '2023-06-16 10:00:00', FALSE),
  ('8e912603-2a7a-48dd-99b3-02a7a868e7a0', 'Sarah Davis', 'JK 7890 LM', '2023-08-15 00:00:00', 'ADMIN', '2023-06-16 10:00:00', 'ADMIN', '2023-06-16 10:00:00', FALSE),
  ('396e52c4-d96c-48b2-8bc2-752c4ed96c7f', 'David Brown', 'MN 2345 OP', '2023-09-01 00:00:00', 'ADMIN', '2023-06-16 10:00:00', 'ADMIN', '2023-06-16 10:00:00', FALSE);


INSERT INTO address (id, created_by, created_date, updated_by, updated_date, mark_for_delete, street, city, state, country, postal_code)
VALUES ('2ad8841d-0d07-4913-824b-2447d5eafac6', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '456 Oak St', 'City', 'State', 'Country', '67890');

INSERT INTO company (id, created_by, created_date, updated_by, updated_date, mark_for_delete, name, address_id)
VALUES ('d3ed4a10-4e95-486a-8247-5253e6a34be3', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'XYZ Company', '2ad8841d-0d07-4913-824b-2447d5eafac6');

INSERT INTO vehicle_type (id, name, created_by, created_date, updated_by, updated_date, mark_for_delete)
VALUES
    ('CAR', 'Car', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false),
    ('MOTORCYCLE', 'Motorcycle', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false),
    ('TRUCK', 'Truck', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false);

INSERT INTO parking_lot (id, created_by, created_date, updated_by, updated_date, mark_for_delete, name, vehicle_capacity, address_id, company_id)
VALUES ('8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'Parking Lot 6', 200, '2ad8841d-0d07-4913-824b-2447d5eafac6', 'd3ed4a10-4e95-486a-8247-5253e6a34be3');

INSERT INTO parking_lot_vehicle_type (id, created_by, created_date, updated_by, updated_date, mark_for_delete, parking_lot_id, vehicle_type_id)
VALUES
   ('7a29c6e1-ef5f-4e82-975e-8af0d07a3f8d', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'CAR'),
   ('a6b6412f-4fa4-4d6a-b556-9cbbfb5d99d3', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'MOTORCYCLE');

INSERT INTO parking_spot (id, created_by, created_date, updated_by, updated_date, mark_for_delete, area, code, vehicle_type_id, is_occupied, parking_lot_id)
VALUES
    ('f0d25c11-36fd-4186-99b4-d4b76d23e0f0', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '1', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('fd7bdc20-2f5e-4f1f-92a2-0c0cfc97d780', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '2', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('d585af43-ec70-4d6a-9edf-3a8226924620', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '3', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('292c6ea1-3be7-4f3b-83dd-1d74a4ef00e9', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '4', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('378b3d9d-0a7f-4755-b64c-0cfc286fe2cd', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '5', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c907efaa-3830-46ef-96ab-518d5fe85389', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '6', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c5d6c95c-739a-4099-927a-541b3e9343a7', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '7', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('378b3d9d-0a7f-4755-b64c-0cfc286fe2ce', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '8', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c907efaa-3830-46ef-96ab-118d5fe85389', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'A', '9', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('f0d25c11-36fd-4186-99b4-24b76d23e0f1', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '1', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('fd7bdc20-2f5e-4f1f-92a2-3c0cfc97d782', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '2', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('d585af43-ec70-4d6a-9edf-4a8226924623', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '3', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('292c6ea1-3be7-4f3b-83dd-5d74a4ef00e4', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '4', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('378b3d9d-0a7f-4755-b64c-6cfc286fe2c5', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '5', 'MOTORCYCLE', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c907efaa-3830-46ef-96ab-718d5fe85386', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '6', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c5d6c95c-739a-4099-927a-841b3e9343a7', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '7', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('378b3d9d-0a7f-4755-b64c-9cfc286fe2cd', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '8', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c907efaa-3830-46ef-96ab-108d5fe85389', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'B', '9', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('f0d25c11-36fd-4186-19b4-d4b76d23e0f0', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '1', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('fd7bdc20-2f5e-4f1f-22a2-0c0cfc97d780', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '2', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('d585af43-ec70-4d6a-3edf-3a8226924620', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '3', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('292c6ea1-3be7-4f3b-43dd-1d74a4ef00e9', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '4', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('378b3d9d-0a7f-4755-56dc-0cfc286fe2cd', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '5', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c907efaa-3830-46ef-66ab-518d5fe85389', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '6', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c5d6c95c-739a-4099-727a-541b3e9343a7', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '7', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('378b3d9d-0a7f-4755-b84c-0cfc286fe2cd', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '8', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('c907efaa-3830-46ef-99ab-518d5fe85389', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'C', '9', 'CAR', false, '8d24318a-17a4-448a-9e2d-f351f1244a33');

INSERT INTO parking_rate (id, created_by, created_date, updated_by, updated_date, mark_for_delete, rate_per_hour)
VALUES ('927c8a49-997b-4baf-b141-3b5d07d7e7a9', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 3000.0);

INSERT INTO payment_method (id, created_by, created_date, updated_by, updated_date, mark_for_delete, name)
VALUES
    ('CASH', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'Cash'),
    ('OVO', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'OVO'),
    ('GOPAY', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'GoPay'),
    ('LINKAJA', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'LinkAja'),
    ('QRIS', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'QRIS');

INSERT INTO parking_lot_payment_method (id, created_by, created_date, updated_by, updated_date, mark_for_delete, parking_lot_id, payment_method_id)
VALUES
    ('1ab8394e-95f5-4d14-823d-9d5c6b5bda6f', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'CASH'),
    ('783b19a3-0860-4c46-bf95-73b33e998f91', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'OVO'),
    ('c6de94d9-2c61-4da9-9e99-eb4e577409f2', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'GOPAY'),
    ('e8f0f62f-ff0e-442f-a95b-2f51b2d96160', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'LINKAJA'),
    ('4f35279f-112b-4d0e-bdfe-6d6f9c079e3a', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, '8d24318a-17a4-448a-9e2d-f351f1244a33', 'QRIS');

INSERT INTO officer (id, created_by, created_date, updated_by, updated_date, mark_for_delete, name, parking_lot_id)
VALUES
    ('ID23894', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'Robert Johnson', '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('ID55012', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'Sarah Wilson', '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('ID75269', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'Christopher Lee', '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('ID12456', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'Jessica Davis', '8d24318a-17a4-448a-9e2d-f351f1244a33'),
    ('ID98637', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', false, 'William Smith', '8d24318a-17a4-448a-9e2d-f351f1244a33');

INSERT INTO check_in_ticket (id, plate_number, member_id, created_by, created_date, updated_by, updated_date, mark_for_delete, officer_id, parking_spot_id, is_active)
VALUES
    ('d4a5a202-4385-4f17-9d28-46a5325c1a29', 'ABC111', null,'ID23894', '2023-06-16 12:00:00', 'ID23894', '2023-06-16 12:00:00', false, 'ID23894', 'f0d25c11-36fd-4186-99b4-d4b76d23e0f0', true),
    ('0dcf4e06-3f61-4082-9139-0f3c7e6e67d1', 'ABC222', null, 'ID55012', '2023-06-16 12:00:00', 'ID55012', '2023-06-17 12:00:00', false, 'ID55012', 'fd7bdc20-2f5e-4f1f-92a2-0c0cfc97d780', true),
    ('c0b729fb-f4d7-4e84-97d0-6f59a2cb5c06', 'ABC333', null, 'ID75269', '2023-06-16 12:00:00', 'ID75269', '2023-06-18 12:00:00', false, 'ID75269', 'd585af43-ec70-4d6a-9edf-3a8226924620', true),
    ('25689d24-36c1-4dc6-8695-0416e8efbf4a', 'ABC444', null, 'ID12456', '2023-06-16 12:00:00', 'ID12456', '2023-06-19 12:00:00', false, 'ID12456', '292c6ea1-3be7-4f3b-83dd-1d74a4ef00e9', true),
    ('6c5c6253-63b0-40da-bdb2-c221fc9ffda5', 'ABC555', null, 'ID98637', '2023-06-16 12:00:00', 'ID98637', '2023-06-20 12:00:00', false, 'ID98637', '378b3d9d-0a7f-4755-b64c-0cfc286fe2cd', true);

UPDATE parking_spot
SET is_occupied = true
WHERE id IN ('f0d25c11-36fd-4186-99b4-d4b76d23e0f0', 'fd7bdc20-2f5e-4f1f-92a2-0c0cfc97d780', 'd585af43-ec70-4d6a-9edf-3a8226924620', '292c6ea1-3be7-4f3b-83dd-1d74a4ef00e9', '378b3d9d-0a7f-4755-b64c-0cfc286fe2cd')
