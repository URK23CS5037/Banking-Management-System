package service;

import database.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerService {

    public int registerCustomer(Customer customer) {

        String query =
                "INSERT INTO customer(name, phone, email, aadhaar) VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAadhaar());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {

                    int customerId = rs.getInt(1);

                    System.out.println("Customer Registered Successfully");
                    System.out.println("Customer ID: " + customerId);

                    return customerId;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
