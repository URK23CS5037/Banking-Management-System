# Banking-Management-System

The Banking Management System is a console-based Java application developed using Core Java, JDBC, and MySQL. It manages customer registration, account creation, login, banking transactions, and transaction history.

The application allows customers to register by entering their name, phone number, email, Aadhaar number, account type, and a four-digit PIN. Input validation is implemented using Java regular expressions to ensure valid customer details.

After registration, the system creates a customer record and an associated bank account in the MySQL database. During login, the account number and PIN are verified using JDBC and SQL queries.

After successful login, customers can perform the following banking operations:
Deposit money
Check account balance
Withdraw money
View mini statement
Logout

For deposits and withdrawals, the account balance is updated in the database. The system checks the available balance before allowing a withdrawal and prevents transactions when the balance is insufficient. Successful deposit and withdrawal transactions are stored in the transaction history.

JDBC is used to connect Java with MySQL. DriverManager establishes the database connection, PreparedStatement executes parameterized SQL queries, and ResultSet retrieves records from the database.

The project applies Core Java concepts including classes and objects, constructors, encapsulation, getters and setters, packages, methods, loops, conditional statements, exception handling, and regular expressions.

The application is divided into database, model, service, and main components to separate database connectivity, data representation, business operations, and application flow.
