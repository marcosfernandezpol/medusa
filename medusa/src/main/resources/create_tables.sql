DROP TABLE User;
DROP TABLE Enterprise;
DROP TABLE Bank_account;
DROP TABLE Transfer_history;


CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL,
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    country VARCHAR(60) NOT NULL,
    city VARCHAR(60) NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE TABLE Enterprise (
    id BIGINT NOT NULL AUTO_INCREMENT,
    enterpriseName VARCHAR(60) COLLATE latin1_bin NOT NULL, 
    acronim VARCHAR(10) COLLATE latin1_bin NOT NULL,
    fundation DATE NOT NULL,
    incomes FLOAT NOT NULL,
    annualBenefits FLOAT NOT NULL,
    CONSTRAINT EnterprisePK PRIMARY KEY (id),
    CONSTRAINT enterpriseNameUnique UNIQUE (enterpriseName)
) ENGINE = InnoDB;

CREATE TABLE  Bank_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    accountNumber BIGINT NOT NULL,
    balance FLOAT NOT NULL,
    CONSTRAINT Bank_accountPK PRIMARY KEY (id),
    CONSTRAINT accountNumberUnique UNIQUE (accountNumber)
) ENGINE = InnoDB;

CREATE TABLE Transfer_history (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATETIME NOT NULL,
    sender BIGINT NOT NULL,
    receiver BIGINT NOT NULL,
    CONSTRAINT TransferPK PRIMARY KEY (id),
    CONSTRAINT SenderFK FOREIGN KEY (sender) REFERENCES User(id),
    CONSTRAINT ReceiverFK FOREIGN KEY (receiver) REFERENCES User(id)
) ENGINE = InnoDB;