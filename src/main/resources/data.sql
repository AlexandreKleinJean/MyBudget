DROP TABLE IF EXISTS "client" CASCADE;
DROP TABLE IF EXISTS "account" CASCADE;
DROP TABLE IF EXISTS "transaction" CASCADE;

-- Client

CREATE TABLE IF NOT EXISTS "client" (
  "id" SERIAL PRIMARY KEY,
  "gender" TEXT,
  "firstname" TEXT,
  "lastname" TEXT,
  "email" TEXT,
  "password" TEXT
);

INSERT INTO "client" ("gender", "firstname", "lastname", "email", "password") VALUES
('Male', 'Bambi', 'Bigcat', 'bambi@cat.com', 'jesuisbambi'),
('Male', 'Macaron', 'Cat', 'macaron@cat.com', 'jesuismacaron'),
('Female', 'Binette', 'Smallcat', 'binette@cat.com', 'jesuisbinette');


-- Account

CREATE TABLE IF NOT EXISTS "account" (
  "id" SERIAL PRIMARY KEY,
  "number" INTEGER,
  "amount" DECIMAL,
  "client_id" INTEGER REFERENCES "client" ("id") ON DELETE CASCADE 
);

INSERT INTO "account" ("number", "amount", "client_id") VALUES
(56, 567.03, 1),
(73, 145.03, 1),
(85, 198.03, 2),
(15, 15.03, 3),
(92, 1567.03, 3);


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

INSERT INTO "transaction" ("id", "subject", "note", "icon", "date", "category", "amount") VALUES
(1, 'Wallmart', 'Groceries', 'assets/images/food.png', '2023-01-01', 'Nourriture', 50),
(2, 'Thai Airlines', 'Holidays', 'assets/images/transport.png', '2023-01-02', 'Transport', 30),
(3, 'Health factory fitness', 'Gym', 'assets/images/sport.png', '2023-01-03', 'Sport', 20),
(4, 'Taxes', 'Taxes', 'assets/images/tax.png', '2023-01-04', 'Factures', 100),
(5, 'Dior', 'Perfume', 'assets/images/shopping.png', '2023-01-05', 'Shopping', 150),
(6, 'Wallmart', 'Miscellaneous', 'assets/images/food.png', '2023-01-06', 'Nourriture', 60),
(7, 'Luftansa', 'Business trip', 'assets/images/transport.png', '2023-01-07', 'Transport', 40),
(8, 'Drums store', 'Play', 'assets/images/sport.png', '2023-01-08', 'Loisirs', 80),
(9, 'Rent', 'Habitation', 'assets/images/rent.png', '2023-01-09', 'Real Estate', 90),
(10, 'Chinese restaurant', 'Business', 'assets/images/shopping.png', '2023-01-10', 'Shopping', 120);
