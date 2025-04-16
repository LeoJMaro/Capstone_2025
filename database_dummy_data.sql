-- Demo data for Capstone Insurance Database
-- This script will populate the database with realistic test data
-- Not Finished Yet!

-- Clear existing data (if any)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE customers;
TRUNCATE TABLE policy;
TRUNCATE TABLE quotes;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS = 1;

-- Insert Customer data
INSERT INTO customers (id, first_name, last_name, email, phone, address, date_of_birth, created_at) VALUES 
(1, 'John', 'Smith', 'john.smith@email.com', '555-123-4567', '123 Main St, Anytown, CA 90210', '1985-06-15', '2024-01-15 09:30:00'),
(2, 'Emily', 'Johnson', 'emily.johnson@email.com', '555-234-5678', '456 Oak Ave, Springfield, IL 62704', '1990-03-22', '2024-01-18 14:45:00'),
(3, 'Michael', 'Williams', 'michael.williams@email.com', '555-345-6789', '789 Pine Rd, Lakeside, NY 14750', '1978-11-30', '2024-01-20 11:15:00'),
(4, 'Sarah', 'Brown', 'sarah.brown@email.com', '555-456-7890', '101 Maple Dr, Mountainview, CO 80401', '1992-08-07', '2024-02-05 16:30:00'),
(5, 'David', 'Jones', 'david.jones@email.com', '555-567-8901', '202 Cedar Ln, Riverside, TX 77401', '1983-04-19', '2024-02-10 10:00:00'),
(6, 'Jennifer', 'Davis', 'jennifer.davis@email.com', '555-678-9012', '303 Elm St, Oceanside, FL 33139', '1975-09-25', '2024-02-15 13:20:00'),
(7, 'Robert', 'Miller', 'robert.miller@email.com', '555-789-0123', '404 Birch Blvd, Highland, WA 98177', '1995-01-11', '2024-02-22 09:45:00'),
(8, 'Linda', 'Wilson', 'linda.wilson@email.com', '555-890-1234', '505 Spruce Way, Meadowville, OR 97301', '1988-07-03', '2024-03-01 15:10:00'),
(9, 'William', 'Taylor', 'william.taylor@email.com', '555-901-2345', '606 Aspen Ct, Valleytown, AZ 85001', '1980-12-28', '2024-03-10 11:30:00'),
(10, 'Patricia', 'Anderson', 'patricia.anderson@email.com', '555-012-3456', '707 Willow Rd, Hillcrest, GA 30301', '1993-05-17', '2024-03-15 14:00:00');

-- Insert User data
INSERT INTO users (customer_id, email, username, password_hash, role) VALUES 
(1, 'john.smith@email.com', 'jsmith', '$2a$10$XVE/0XiDUOfYVJsQSZSK7OMk3.DjvMVGocDAgQs8pVeqg7JMUjQee', 'CUSTOMER'), -- password: password123
(2, 'emily.johnson@email.com', 'ejohnson', '$2a$10$qR8J5UfVYZjxJ9yJPKsGrewF5xtXaauKZJLvUJu.mX5QqcUimdUuW', 'CUSTOMER'), -- password: securepass
(3, 'michael.williams@email.com', 'mwilliams', '$2a$10$bLSfjRLVkvVQ8iGPQ0bwzO/WO3zQMhhq2JVG8VgI.M1dVrZKtIiT2', 'CUSTOMER'), -- password: michael123
(4, 'sarah.brown@email.com', 'sbrown', '$2a$10$4pQkLfQu3d7tQWZK5sZ1beJ8EuZ.KaZ5OCDh87qJ1NdJ9/p90ZPtG', 'CUSTOMER'), -- password: brown2024
(5, 'david.jones@email.com', 'djones', '$2a$10$uJEZ4tbEfMrvGxs2vUmkT.BkEqJrVCNBBD3XSL5KPCZMxfHqyZjHe', 'ADMIN'), -- password: admin1234
(6, 'jennifer.davis@email.com', 'jdavis', '$2a$10$QCJiYs4HdXdBGDQ3YpBuE.H71TcJS.QIB8O4i.sGxl3Lfm4ZQVKbq', 'CUSTOMER'), -- password: jenpass
(7, 'robert.miller@email.com', 'rmiller', '$2a$10$V8Z3yzXNKVcMG0zH.d7a4.DfqMjLKEZWkAKk5AHIX4n5O9v/4wY5m', 'CUSTOMER'), -- password: robert2024
(8, 'linda.wilson@email.com', 'lwilson', '$2a$10$iSrPVd8KsE4r3WFvBZ4n1.A88ZuJXkM/.UOmn4HdZA4KnTr8mWXr.', 'CUSTOMER'), -- password: lindapass
(9, 'william.taylor@email.com', 'wtaylor', '$2a$10$k2K2VUMNXDSMRaBwvMb8xOQT5B3RvEVeNfZcxKBd48lAVV1CIShOu', 'CUSTOMER'), -- password: william123
(10, 'patricia.anderson@email.com', 'panderson', '$2a$10$LPQhSIo1JnIq5AIyQ5MfSO6cPT2y9VXzJ.HQdA8J3rOzrTtGpgz2S', 'AGENT'); -- password: agent2024

-- Insert Auto Policy data
INSERT INTO policy (policy_type, policy_id, base_premium, customer_id, end_date, premium, start_date, status, 
                   age_factor, accident_factor, vehicle_accidents, vehicle_make, vehicle_model, vehicle_year, vehicle_factor) VALUES 
('AUTO', 1, 750.00, 1, '2025-01-15', 995.00, '2024-01-15', 'ACTIVE', 
      1.05, 1.00, 0, 'Honda', 'Civic', 2020, 1.2),
('AUTO', 2, 750.00, 2, '2025-02-18', 1087.50, '2024-02-18', 'ACTIVE', 
      0.95, 1.20, 1, 'Toyota', 'Camry', 2022, 1.0),
('AUTO', 3, 750.00, 3, '2025-01-20', 1125.00, '2024-01-20', 'ACTIVE', 
      1.15, 1.00, 0, 'Ford', 'F-150', 2021, 1.25),
('AUTO', 4, 750.00, 4, '2025-02-05', 870.00, '2024-02-05', 'ACTIVE', 
      0.90, 1.00, 0, 'Chevrolet', 'Malibu', 2019, 1.16),
('AUTO', 5, 750.00, 6, '2025-02-15', 1235.00, '2024-02-15', 'ACTIVE', 
      1.20, 1.30, 2, 'BMW', '3 Series', 2023, 1.0),
('AUTO', 6, 750.00, 7, '2025-02-22', 770.00, '2024-02-22', 'ACTIVE', 
      0.85, 1.00, 0, 'Hyundai', 'Elantra', 2020, 1.10),
('AUTO', 7, 750.00, 9, '2025-03-10', 1031.25, '2024-03-10', 'ACTIVE', 
      1.10, 1.00, 0, 'Subaru', 'Outback', 2021, 1.25);

-- Insert Home Policy data
INSERT INTO policy (policy_type, policy_id, base_premium, customer_id, end_date, premium, start_date, status, 
                   additional, age, dwelling_type, heating_type, home_value, location, heating_factor, location_factor, age_factor) VALUES 
('HOME', 8, 500.00, 1, '2025-01-15', 1500.00, '2024-01-15', 'ACTIVE', 
      100.00, 25, 'Single Family', 'Gas', 350000.00, 'Urban', 1.0, 1.2, 1),
('HOME', 9, 500.00, 5, '2025-02-10', 1320.00, '2024-02-10', 'ACTIVE', 
      150.00, 15, 'Townhouse', 'Electric', 280000.00, 'Suburban', 1.1, 1.0, 1),
('HOME', 10, 500.00, 6, '2025-02-15', 1755.00, '2024-02-15', 'ACTIVE', 
      200.00, 40, 'Single Family', 'Oil', 420000.00, 'Rural', 1.2, 0.9, 1),
('HOME', 11, 500.00, 8, '2025-03-01', 1140.00, '2024-03-01', 'ACTIVE', 
      50.00, 5, 'Condo', 'Electric', 220000.00, 'Urban', 1.0, 1.2, 1),
('HOME', 12, 500.00, 10, '2025-03-15', 1312.50, '2024-03-15', 'ACTIVE', 
      75.00, 10, 'Duplex', 'Gas', 300000.00, 'Suburban', 1.0, 1.1, 1);

-- Insert Quote data
INSERT INTO quotes (quote_id, generated_date, policy_type, premium, customer_id) VALUES 
(1, '2024-01-10', 'AUTO', 995.00, 1),
(2, '2024-01-12', 'HOME', 1500.00, 1),
(3, '2024-02-15', 'AUTO', 1087.50, 2),
(4, '2024-01-18', 'AUTO', 1125.00, 3),
(5, '2024-02-03', 'AUTO', 870.00, 4),
(6, '2024-02-08', 'HOME', 1320.00, 5),
(7, '2024-02-13', 'AUTO', 1235.00, 6),
(8, '2024-02-14', 'HOME', 1755.00, 6),
(9, '2024-02-20', 'AUTO', 770.00, 7),
(10, '2024-02-28', 'HOME', 1140.00, 8),
(11, '2024-03-08', 'AUTO', 1031.25, 9),
(12, '2024-03-13', 'HOME', 1312.50, 10);