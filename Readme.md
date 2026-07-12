# 🏦 Bank Management System (Java)

![Java](https://img.shields.io/badge/Java-JDK25-blue)
![Status](https://img.shields.io/badge/Project-Stage%202-brightgreen)
![OOP](https://img.shields.io/badge/OOP-Concepts-orange)

A Java console-based Bank Management System that performs CRUD (Create, Read, Update, Delete) operations along with core banking transactions (Deposit, Withdraw, Transfer) using JDBC and MySQL, while demonstrating Object-Oriented Programming (OOP), Exception Handling, and Database Transaction Management.

---

## ✨ Features

- 👤 Customer Registration & Login
- 🏦 Open New Account (Savings / Current)
- 💰 Deposit Money
- 💸 Withdraw Money
- 🔄 Fund Transfer between Accounts (Atomic — Commit/Rollback)
- 📋 View Account Details & Balance
- 🧾 Transaction History / Statement
- 🛠️ Admin Panel — View/Manage Customers & Accounts
- 💾 Persistent data storage using MySQL
- ⚠️ Input validation and exception handling

---
## 🛠️ Tech Stack

<p align="left">
  <img src="https://skillicons.dev/icons?i=java,mysql,git,github,idea" />
</p>

**Java • JDBC • MySQL • SQL • Git • GitHub • IntelliJ IDEA**

---

## 📚 Concepts Used

✔️ Object-Oriented Programming (OOP)

✔️ CRUD Operations

✔️ Database Transactions (Commit / Rollback)

✔️ Exception Handling

✔️ Input Validation

✔️ JDBC API

✔️ PreparedStatement

✔️ Try-with-Resources

---

## 🚧 Project Roadmap

- ✅ Stage 1: Model Classes — Customer, Account, Transaction (Completed)
- ⏳ Stage 2: JDBC + MySQL Integration (DAO Layer) (In Progress)
- ⏳ Stage 3: Core Banking Logic — Deposit, Withdraw, Transfer (Upcoming)
- ⏳ Stage 4: Admin Panel & Reports (Upcoming)

---

## 📁 Project Structure

```text
BANK-MANAGEMENT-SYSTEM/
│
├── src/
│   ├── Main.java                  # Application entry point
│   ├── Customer.java              # Customer model class
│   ├── Account.java               # Account model class
│   ├── Transaction.java           # Transaction model class
│   ├── CustomerDAO.java           # Customer database operations
│   ├── AccountDAO.java            # Account database operations
│   ├── TransactionDAO.java        # Transaction database operations
│   ├── Manager.java               # Account CRUD & business logic
│   ├── BankService.java           # Deposit, Withdraw, Transfer logic
│   ├── AdminService.java          # Admin-side operations
│   └── DBConnectionExample.java   # Database connection template
│
├── database.sql                   # Database & table creation script
├── .gitignore                     # Git ignored files
└── README.md                      # Project documentation
```
---

## 📸 Screenshots

*(To be added as features are completed)*

---


## 👨‍💻 Author

**Shubham Joshi**

🔗 GitHub: <https://github.com/dev-jshubham>

📂 Repository: <https://github.com/dev-jshubham/bank-management-system>