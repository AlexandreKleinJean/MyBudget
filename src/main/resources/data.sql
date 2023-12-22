DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS "account" CASCADE;
DROP TABLE IF EXISTS "transaction" CASCADE;

-- User

CREATE TABLE IF NOT EXISTS "user" (
  "id" SERIAL PRIMARY KEY,
  "gender" TEXT,
  "firstname" TEXT,
  "lastname" TEXT,
  "email" TEXT,
  "password" TEXT
);

INSERT INTO "user" ("gender", "firstname", "lastname", "email", "password") VALUES
('Male', 'Bambi', 'Bigcat', 'bambi@cat.com', 'jesuisbambi'),
('Male', 'Macaron', 'Cat', 'macaron@cat.com', 'jesuismacaron'),
('Female', 'Binette', 'Smallcat', 'binette@cat.com', 'jesuisbinette');


-- Account

CREATE TABLE IF NOT EXISTS "account" (
  "id" SERIAL PRIMARY KEY,
  "number" INTEGER,
  "amount" DECIMAL,
  "user_id" INTEGER REFERENCES "user" ("id") ON DELETE CASCADE 
);

INSERT INTO "account" ("number", "amount", "user_id") VALUES
(56, 567.03, 1),
(73, 145.03, 1),
(85, 198.03, 2),
(15, 15.03, 3),
(92, 1567.03, 3);


-- Transaction

CREATE TABLE IF NOT EXISTS "transaction" (
  "id" SERIAL PRIMARY KEY,
  "date" date,
  "category" TEXT,
  "amount" DECIMAL,
  "account_id" INTEGER REFERENCES "account" ("id") ON DELETE CASCADE 
);

INSERT INTO "transaction" ("date", "category", "amount", "account_id") VALUES
('2023-01-01', 'Nourriture', 50, 1),
('2023-01-02', 'Transport', 30, 4),
('2023-01-03', 'Loisirs', 20, 2),
('2023-01-04', 'Factures', 100, 5),
('2023-01-05', 'Shopping', 150, 4),
('2023-01-06', 'Nourriture', 60, 1),
('2023-01-07', 'Transport', 40, 2),
('2023-01-08', 'Loisirs', 80, 3),
('2023-01-09', 'Factures', 90, 3),
('2023-01-10', 'Shopping', 120, 4);