DROP TABLE IF EXISTS "client" CASCADE;
DROP TABLE IF EXISTS "account" CASCADE;
DROP TABLE IF EXISTS "transaction" CASCADE;
DROP TABLE IF EXISTS "forecast" CASCADE;


-- Forecast

CREATE TABLE IF NOT EXISTS "forecast" (
  "id" SERIAL PRIMARY KEY,
  "salary" DECIMAL,
  "foodRate" INTEGER,
  "transportRate" INTEGER,
  "sportRate" INTEGER,
  "invoiceRate" INTEGER,
  "shoppingRate" INTEGER,
  "leisureRate" INTEGER,
  "realEstateRate" INTEGER
);

INSERT INTO "forecast" ("salary", "foodRate", "transportRate", "sportRate", "invoiceRate", "shoppingRate", "leisureRate", "realEstateRate") 
VALUES (1000.0, 20, 10, 5, 15, 10, 10, 30);


-- Client

CREATE TABLE IF NOT EXISTS "client" (
  "id" SERIAL PRIMARY KEY,
  "gender" TEXT,
  "firstname" TEXT,
  "lastname" TEXT,
  "email" TEXT,
  "password" TEXT,
  "forecast_id" INTEGER REFERENCES "forecast" ("id") DEFAULT 1
);

INSERT INTO "client" ("gender", "firstname", "lastname", "email", "password") VALUES
('Male', 'Bambi', 'Big', 'bambi@gmail.com', '$2a$10$HSKZvC2WkQCW2DA95U1S0OYUvNOoXwytNm7HkhRhdXuuFgzOGwhpy'),
('Male', 'Macaron', 'Normal', 'macaron@gmail.com', '$2a$10$CEbuflErrdNGcnd2VsLPc.mlSRO9ZLrqylZOZiAct2AvgxrXilGSq'),
('Female', 'Binette', 'Small', 'binette@gmail.com', '$2a$10$UXvcS73mQlFDwOWz2ERgD.yYIvhmG0yqV4vqR76F1jSPf8.CYcXW2');


-- Account

CREATE TABLE IF NOT EXISTS "account" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT,
  "bank" TEXT,
  "client_id" INTEGER REFERENCES "client" ("id") ON DELETE CASCADE 
);

INSERT INTO "account" ("name", "bank", "client_id") VALUES
('Mastercard', 'BIL', 1),
('Visa', 'Crédit Agricole', 1),
('Mastercard', 'Boursorama', 2),
('Mastercard', 'Boursorama', 3),
('Visa electron', 'HHM', 3);


-- Transaction

CREATE TABLE IF NOT EXISTS "transaction" (
  "id" SERIAL PRIMARY KEY,
  "subject" TEXT,
  "note" TEXT,
  "icon" TEXT,
  "date" DATE,
  "category" TEXT,
  "amount" DECIMAL,
  "account_id" INTEGER REFERENCES "account" ("id") ON DELETE CASCADE 
);

INSERT INTO "transaction" ("id", "subject", "note", "icon", "date", "category", "amount", "account_id") VALUES
(1, 'Wallmart', 'Groceries', 'assets/images/food.png', '2023-01-01', 'Food', 50, 2),
(2, 'Thai Airlines', 'Holidays', 'assets/images/transport.png', '2023-01-02', 'Transport', 30, 3),
(3, 'Health factory fitness', 'Gym', 'assets/images/sport.png', '2023-01-03', 'Sport', 20, 4),
(4, 'Taxes', 'Taxes', 'assets/images/tax.png', '2023-01-04', 'Invoice', 100, 1),
(5, 'Dior', 'Perfume', 'assets/images/shopping.png', '2023-01-05', 'Shopping', 150, 5),
(6, 'Wallmart', 'Miscellaneous', 'assets/images/food.png', '2023-01-06', 'Food', 60, 5),
(7, 'Luftansa', 'Business trip', 'assets/images/transport.png', '2023-01-07', 'Transport', 40, 2),
(8, 'Drums store', 'Play', 'assets/images/sport.png', '2023-01-08', 'Leisure', 80, 4),
(9, 'Rent', 'Habitation', 'assets/images/rent.png', '2023-01-09', 'Real Estate', 90, 3),
(10, 'Chinese restaurant', 'Business', 'assets/images/shopping.png', '2023-01-10', 'Shopping', 120, 1);