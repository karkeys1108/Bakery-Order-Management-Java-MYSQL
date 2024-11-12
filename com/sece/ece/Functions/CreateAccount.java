package com.sece.ece.Functions;

import com.sece.ece.entities.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static com.sece.ece.Functions.BakeryItemsFunctions.OrderStatus;
import static com.sece.ece.Functions.BakeryItemsFunctions.displayItems;
import static com.sece.ece.Functions.OrderFunctions.showLogHistories;

public class CreateAccount {

    // Method to handle opening an existing account


    public static void createAccount(Connection conn, List<Customer> customers) {
        int customer_id = getCustomerId(conn);
        try {
            String query = "INSERT INTO customer (customer_id, customer_name, customer_email, customer_phone, customer_address) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, customer_id);
            preparedStatement.setString(2, customers.get(0).getCustomer_Name());
            preparedStatement.setString(3, customers.get(0).getCustomer_email());
            preparedStatement.setString(4, customers.get(0).getCustomer_phone());
            preparedStatement.setString(5, customers.get(0).getCustomer_address());
            preparedStatement.executeUpdate();
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static int getCustomerId(Connection conn) {
        int customer_id = 0;
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT count(*) FROM customer";
            preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_id + 1;
    }

    // Method to find an existing customer by ID
    public static Customer foundCustomer(Connection conn, int customer_id) {
        Customer customer = null;
        try {
            String sql = "SELECT * FROM customer WHERE customer_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customer_Id = rs.getInt("customer_id");
                String customer_name = rs.getString("customer_name");
                String customer_email = rs.getString("customer_email");
                String customer_phoneNumber = rs.getString("customer_phone");
                String customer_address = rs.getString("customer_address");

                customer = new Customer(customer_Id, customer_name, customer_email, customer_phoneNumber, customer_address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

}
