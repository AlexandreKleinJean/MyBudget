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
  "accountNumber" INTEGER,
  "client_id" INTEGER REFERENCES "client" ("id") ON DELETE CASCADE 
);

INSERT INTO "account" ("client_id", "accountNumber") VALUES
(1, 56),
(1, 73),
(2, 85),
(3, 15),
(3, 92);


-- Transaction

CREATE TABLE IF NOT EXISTS "transaction" (
  "id" SERIAL PRIMARY KEY,
  "date" date,
  "category" TEXT,
  "amount" INTEGER,
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