DROP TABLE IF EXISTS TBL_EMPLOYEES;
DROP TABLE IF EXISTS TBL_CUSTOMER;
DROP TABLE IF EXISTS TBL_PAYEE;
 
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

CREATE TABLE TBL_CUSTOMER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) DEFAULT NULL,
  last_name VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) DEFAULT NULL,
  accountno INT NOT NULL,
  MOBILENO VARCHAR(30) DEFAULT NULL,
  ADDRESS VARCHAR(250) DEFAULT NULL,
  BALANCE DOUBLE,
  PINCODE VARCHAR(30) 
);

CREATE TABLE TBL_PAYEE (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) DEFAULT NULL,
  last_name VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) DEFAULT NULL,
  accountno INT NOT NULL,
  MOBILENO VARCHAR(30) DEFAULT NULL,
  ADDRESS VARCHAR(250) DEFAULT NULL,
  BALANCE DOUBLE,
  PINCODE VARCHAR(30) DEFAULT NULL,
  IFSC VARCHAR(20) NOT null
);