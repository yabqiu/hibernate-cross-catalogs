CREATE SCHEMA mart;
CREATE SCHEMA client1;
CREATE SCHEMA client2;

--DROP TABLE IF EXISTS mart.account;
CREATE TABLE mart.account(
	id INT,
	name VARCHAR(32)
);

--DROP TABLE IF EXISTS mart.history;
CREATE TABLE mart.history(
	id INT,
	accountId INT,
	lastUpdate VARCHAR(10)
);

------------
--DROP TABLE IF EXISTS client1.account;
CREATE TABLE client1.account(
	id INT,
	name VARCHAR(32)
);

--DROP TABLE IF EXISTS client1.history;
CREATE TABLE client1.history(
	id INT,
	accountId INT,
	lastUpdate VARCHAR(10)
);

--------------------
--DROP TABLE IF EXISTS client2.account;
CREATE TABLE client2.account(
	id INT,
	name VARCHAR(32)
);

--DROP TABLE IF EXISTS client2.history;
CREATE TABLE client2.history(
	id INT,
	accountId INT,
	lastUpdate VARCHAR(10)
);