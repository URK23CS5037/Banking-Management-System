package service;

import database.DBConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountService {

    // Create Account
    public int createAccount(Account account) {

    String query =
            "INSERT INTO account(customer_id, account_type, pin) VALUES (?, ?, ?)";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query,
                        PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1, account.getCustomerId());
        ps.setString(2, account.getAccountType());
        ps.setString(3, account.getPin());

        int rows =
                ps.executeUpdate();

        if (rows > 0) {

            ResultSet rs =
                    ps.getGeneratedKeys();

            if (rs.next()) {

                int accountNumber =
                        rs.getInt(1);

                System.out.println("Account Created Successfully");

                return accountNumber;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return -1;
}

    // Login Method
    public boolean login(int accountNumber, String pin) {

        String query =
                "SELECT * FROM account WHERE account_number = ? AND pin = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, accountNumber);
            ps.setString(2, pin);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //deposit

    public void depositMoney(int accountNumber,double amount) {

    String query =
            "UPDATE account SET balance = balance + ? WHERE account_number = ?";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setDouble(1, amount);
        ps.setInt(2, accountNumber);

        int rows =
                ps.executeUpdate();

        if (rows > 0) {

    saveTransaction(accountNumber,
            "Deposit",
            amount);

    System.out.println("Money Deposited Successfully");

} else {
    System.out.println("Deposit Failed");
}

    } catch (Exception e) {
        e.printStackTrace();
    }
}

//check balance
public double checkBalance(int accountNumber) {

    String query =
            "SELECT balance FROM account WHERE account_number = ?";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, accountNumber);

        ResultSet rs =
                ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("balance");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

//withdraw
public void withdrawMoney(int accountNumber,
                           double amount) {

    double currentBalance =
            checkBalance(accountNumber);

    if (amount > currentBalance) {
        System.out.println("Insufficient Balance");
        return;
    }

    String query =
            "UPDATE account SET balance = balance - ? WHERE account_number = ?";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setDouble(1, amount);
        ps.setInt(2, accountNumber);

        int rows =
                ps.executeUpdate();

        if (rows > 0) {

    saveTransaction(accountNumber,
            "Withdraw",
            amount);

    System.out.println("Money Withdrawn Successfully");

} else {
    System.out.println("Withdrawal Failed");
}

    } catch (Exception e) {
        e.printStackTrace();
    }
}

//mini statement
public void saveTransaction(int accountNumber,
                            String type,
                            double amount) {

    String query =
            "INSERT INTO transaction_history(account_number, transaction_type, amount) VALUES (?, ?, ?)";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, accountNumber);
        ps.setString(2, type);
        ps.setDouble(3, amount);

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

//mini statement
public void miniStatement(int accountNumber) {

    String query =
            "SELECT transaction_type, amount, transaction_date " +
            "FROM transaction_history " +
            "WHERE account_number = ? " +
            "ORDER BY transaction_id DESC";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, accountNumber);

        ResultSet rs =
                ps.executeQuery();

        System.out.println("\n===== MINI STATEMENT =====");

        while (rs.next()) {

            String type =
                    rs.getString("transaction_type");

            double amount =
                    rs.getDouble("amount");

            String date =
                    rs.getString("transaction_date");

            System.out.println(type + " | Rs " + amount + " | " + date);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
