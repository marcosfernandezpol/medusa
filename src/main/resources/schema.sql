DROP TABLE IF EXISTS ActionPriceHistoric;
DROP TABLE IF EXISTS OrderLine;
DROP TABLE IF EXISTS AnnualBenefits;
DROP TABLE IF EXISTS Enterprise;
DROP TABLE IF EXISTS Bank_account;
DROP TABLE IF EXISTS Transfer_history;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL,
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    country VARCHAR(60),
    city VARCHAR(60),
    email VARCHAR(60) NOT NULL,
    balance FLOAT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (login)
);

CREATE TABLE Enterprise (
    id BIGINT NOT NULL AUTO_INCREMENT,
    creatorId BIGINT NOT NULL,
    enterpriseName VARCHAR(60) NOT NULL, 
    acronim VARCHAR(10) NOT NULL,
    fundation DATE,
    incomes FLOAT NOT NULL,
    stock BIGINT NOT NULL,
    stockPrice FLOAT NOT NULL,
    avaliable BIT,
    CONSTRAINT EnterprisePK PRIMARY KEY (id),
    CONSTRAINT creatorFK FOREIGN KEY (creatorId) REFERENCES User(id),
    CONSTRAINT enterpriseNameUnique UNIQUE (enterpriseName)
);

CREATE TABLE ActionPriceHistoric (
	id BIGINT NOT NULL AUTO_INCREMENT,
	enterpriseId BIGINT NOT NULL,
	date DATETIME NOT NULL,
	price BIGINT NOT NULL,
	CONSTRAINT ActionPriceHistoricPK PRIMARY KEY (id),
	CONSTRAINT EnterpriseActionPriceHistoricFK FOREIGN KEY (enterpriseId) REFERENCES Enterprise(id)
);


CREATE TABLE AnnualBenefits(
	id BIGINT NOT NULL AUTO_INCREMENT,
	enterpriseId BIGINT NOT NULL,
	year int NOT NULL UNIQUE,
	benefits float not NULL,
	CONSTRAINT AnnualBenefitsPK PRIMARY KEY (id),
	CONSTRAINT AnnualEnterpriseFK FOREIGN KEY (enterpriseId) REFERENCES Enterprise(id)
);

CREATE TABLE  Bank_account (
    accountNumber BIGINT NOT NULL,
    balance FLOAT NOT NULL,
    owner BIGINT NOT NULL,
    CONSTRAINT Bank_accountPK PRIMARY KEY (accountNumber),
    CONSTRAINT Bank_accountFK FOREIGN KEY (owner) REFERENCES User(id)
);

CREATE TABLE Transfer_history (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATETIME NOT NULL,
    sender BIGINT NOT NULL,
    receiver BIGINT NOT NULL,
    CONSTRAINT TransferPK PRIMARY KEY (id),
    CONSTRAINT SenderFK FOREIGN KEY (sender) REFERENCES User(id),
    CONSTRAINT ReceiverFK FOREIGN KEY (receiver) REFERENCES User(id)
);

CREATE TABLE OrderLine (
	id BIGINT NOT NULL AUTO_INCREMENT,
	requestDate DATETIME NOT NULL,
	orderType TINYINT NOT NULL,
	orderLineType TINYINT NOT NULL,
	userId BIGINT,
	price FLOAT NOT NULL,
	number INT  NOT NULL,
	enterpriseId BIGINT NOT NULL,
	avaliable BIT,
	deadline DATE,
	CONSTRAINT Order_linePK PRIMARY KEY (id),
	CONSTRAINT OwnerFK FOREIGN KEY (userId) REFERENCES User(id),
	CONSTRAINT enterpriseFK FOREIGN KEY (enterpriseId) REFERENCES Enterprise(id)
);