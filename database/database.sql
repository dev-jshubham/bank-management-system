-- =========================================================
-- Bank Management System Database Schema
-- Database: project2_bms
-- Description:
-- This file creates all tables required for the
-- Bank Management System project.
-- =========================================================

-- =========================================================
-- Create Database
-- =========================================================
CREATE DATABASE IF NOT EXISTS project2_bms;
USE project2_bms;
-- =========================================================
-- Customer Table
-- Stores customer personal and login information.
-- =========================================================
CREATE TABLE IF NOT EXISTS customer(
                             customerId INT PRIMARY KEY auto_increment,
                             name varchar(30) NOT NULL,
                             dob DATE NOT NULL,
                             gender ENUM('Male', 'Female', 'Other') NOT NULL,
                             phoneNumber varchar(13) unique NOT NULL,
                             email varchar(30) unique,
                             address varchar(50) NOT NULL,
                             idProofType varchar(20) NOT NULL,
                             idProofNumber varchar(20) unique,
                             password varchar(20) NOT NULL,
                             registrationDate DATETIME NOT NULL,
                             active BOOLEAN not null
);
-- =========================================================
-- Account Table
-- Stores bank account details for each customer.
-- =========================================================
CREATE TABLE IF NOT EXISTS account(
                            accountNumber varchar(12) primary key,
                            customerId int not null,
                            accountType enum('SavingsAccount','CurrentAccount') not null,
                            balance decimal(12,2) not null,
                            status ENUM('ACTIVE','BLOCKED','CLOSED') not null default 'ACTIVE',
                            openedDate datetime not null,
                            pin varchar(4) not null,
                            foreign key (customerId)
                            references customer(customerId)
);
-- =========================================================
-- Transactions Table
-- Stores complete transaction history of each account.
-- =========================================================
CREATE TABLE IF NOT EXISTS transaction(
                            transactionId int AUTO_INCREMENT primary key,
                            accountNumber varchar(12) not null,
                            transactionType ENUM('DEPOSIT','WITHDRAW','TRANSFER_IN','TRANSFER_OUT') not null ,
                            amount decimal(12,2) not null,
                            balanceAfter decimal(12,2) not null,
                            transactionDate DATETIME NOT NULL,
                            foreign key (accountNumber)
                            references account(accountNumber)
);
