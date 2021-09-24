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
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (login)
);

CREATE TABLE Enterprise (
    id BIGINT NOT NULL AUTO_INCREMENT,
    enterpriseName VARCHAR(60) NOT NULL, 
    acronim VARCHAR(10) NOT NULL,
    fundation DATE,
    incomes FLOAT NOT NULL,
    annualBenefits FLOAT NOT NULL,
    CONSTRAINT EnterprisePK PRIMARY KEY (id),
    CONSTRAINT enterpriseNameUnique UNIQUE (enterpriseName)
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
