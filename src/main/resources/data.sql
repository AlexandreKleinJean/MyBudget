DROP TABLE IF EXISTS "client" CASCADE;
DROP TABLE IF EXISTS "account" CASCADE;
DROP TABLE IF EXISTS "transaction" CASCADE;
DROP TABLE IF EXISTS "forecast" CASCADE;


-- Forecast

CREATE TABLE IF NOT EXISTS "forecast" (
  "id" SERIAL PRIMARY KEY,
  "salary" DECIMAL,
  "food_rate" INTEGER,
  "transport_rate" INTEGER,
  "sport_rate" INTEGER,
  "invoice_rate" INTEGER,
  "shopping_rate" INTEGER,
  "leisure_rate" INTEGER,
  "real_estate_rate" INTEGER
);

INSERT INTO "forecast" ("salary", "food_rate", "transport_rate", "sport_rate", "invoice_rate", "shopping_rate", "leisure_rate", "real_estate_rate") 
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

INSERT INTO "client" ("gender", "firstname", "lastname", "email", "password", "forecast_id") VALUES
('Male', 'Bambi', 'Big', 'bambi@gmail.com', '$2a$10$HSKZvC2WkQCW2DA95U1S0OYUvNOoXwytNm7HkhRhdXuuFgzOGwhpy', 1),
('Male', 'Macaron', 'Normal', 'macaron@gmail.com', '$2a$10$CEbuflErrdNGcnd2VsLPc.mlSRO9ZLrqylZOZiAct2AvgxrXilGSq', 1),
('Female', 'Binette', 'Small', 'binette@gmail.com', '$2a$10$UXvcS73mQlFDwOWz2ERgD.yYIvhmG0yqV4vqR76F1jSPf8.CYcXW2', 1);


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

INSERT INTO "transaction" ("subject", "note", "icon", "date", "category", "amount", "account_id") VALUES
('Wallmart', 'Groceries', 'assets/images/food.png', '2023-01-01', 'Food', 50, 2),
('Thai Airlines', 'Holidays', 'assets/images/transport.png', '2023-01-02', 'Transport', 30, 3),
('Health factory fitness', 'Gym', 'assets/images/sport.png', '2023-01-03', 'Sport', 20, 4),
('Taxes', 'Taxes', 'assets/images/tax.png', '2023-01-04', 'Invoice', 100, 1),
('Dior', 'Perfume', 'assets/images/shopping.png', '2023-01-05', 'Shopping', 150, 5),
('Wallmart', 'Miscellaneous', 'assets/images/food.png', '2023-01-06', 'Food', 60, 5),
('Luftansa', 'Business trip', 'assets/images/transport.png', '2023-01-07', 'Transport', 40, 2),
('Drums store', 'Play', 'assets/images/sport.png', '2023-01-08', 'Leisure', 80, 4),
('Rent', 'Habitation', 'assets/images/rent.png', '2023-01-09', 'Real Estate', 90, 3),
('Chinese restaurant', 'Business', 'assets/images/shopping.png', '2023-01-10', 'Shopping', 120, 1);