# Banking Management System

A simple Banking Management System developed using **Java, JDBC, and MySQL**. This project allows users to create bank accounts, check balances, deposit money, and withdraw money.

All account details and balances are stored permanently in a MySQL database.

## Technologies Used

* Java
* MySQL
* JDBC (Java Database Connectivity)
* Visual Studio Code
* MySQL Workbench

## Features

* Create a new bank account
* Check account balance
* Deposit money
* Withdraw money
* Validate user inputs
* Store account details in MySQL
* Update account balances automatically
* Retrieve existing account information

## Project Structure

```text
BankingManagementSystem/
│
├── BMS.java
├── Account.java
├── DBConnection.java
├── README.md
│
└── lib/
    └── mysql-connector-j-26.7.0.jar
```

## Database Setup

**Step 1:** Open MySQL Workbench.

**Step 2:** Create the database.

```sql
CREATE DATABASE IF NOT EXISTS banking_system;

USE banking_system;
```

**Step 3:** Create the accounts table.

```sql
CREATE TABLE IF NOT EXISTS accounts (
    acno VARCHAR(20) PRIMARY KEY,
    acname VARCHAR(100) NOT NULL,
    balance DECIMAL(12,2) DEFAULT 0.00
);
```

**Step 4:** View account details.

```sql
SELECT * FROM accounts;
```

## Java Files Explanation

### 1. BMS.java

This is the main Java file.

Functions:

* Accept account details
* Display the banking menu
* Process user choices
* Validate user inputs
* Call database methods

### 2. Account.java

This class represents a bank account.

It contains account-related properties and methods. The current database-connected menu uses `DBConnection.java` directly.

### 3. DBConnection.java

This file manages the MySQL database connection.

Functions:

* Connect Java to MySQL
* Save account details
* Retrieve account information
* Check account balances
* Update balances after deposits
* Update balances after withdrawals

## How to Run the Project

1. Install Java JDK.
2. Install MySQL Server and MySQL Workbench.
3. Open the project folder in Visual Studio Code.
4. Download MySQL Connector/J.
5. Add the JDBC JAR file to the project's Referenced Libraries.
6. Create the `banking_system` database.
7. Configure the database connection in `DBConnection.java`.
8. Run `BMS.java`.
9. Use the banking menu to perform operations.

## Database Connection

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/banking_system",
    "root",
    "YOUR_MYSQL_PASSWORD"
);
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password.

**Security Note:** Never upload your actual database password to GitHub. Use environment variables to store credentials in shared projects.

## Banking Menu

```text
===== BANKING MANAGEMENT SYSTEM =====

1. Check Balance
2. Deposit
3. Withdraw
4. Exit

Enter Your Choice:
```

## Sample Output

```text
===== BANKING MANAGEMENT SYSTEM =====

Enter Account Number: 1001
Enter Account Holder Name: Kiruba

Account Created Successfully!

===== BANKING MENU =====

1. Check Balance
2. Deposit
3. Withdraw
4. Exit

Enter Your Choice: 2

Enter Deposit Amount: 5000

Amount Deposited Successfully!

Enter Your Choice: 3

Enter Withdraw Amount: 2000

Amount Withdrawn Successfully!

Enter Your Choice: 1

Current Balance: Rs. 3000.00
```

## OOP Concepts

This project introduces the following Java concepts:

* Classes and Objects
* Constructors
* Encapsulation
* Methods
* Exception Handling
* Input Validation

The project also demonstrates JDBC database connectivity.

## Future Improvements

* User Login System
* PIN Authentication
* Transaction History
* Money Transfer
* Savings and Current Accounts
* Java Swing GUI
* Admin Dashboard

## Conclusion

The Banking Management System is a Java-based application integrated with a MySQL database using JDBC.

It demonstrates basic banking operations, database connectivity, SQL queries, and Java programming concepts.

This project is developed for educational purposes.

---

**Project Name:** Banking Management System

**Programming Language:** Java

**Database:** MySQL

**Database Connectivity:** JDBC

**Development Environment:** Visual Studio Code

**Project Type:** Console-Based Application
