package com.sece.ece.Functions;

import com.sece.ece.Exceptions.OrderNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sece.ece.Functions.OrderFunctions.OrderBakeryItems;

public class BakeryItemsFunctions {

    public static void OrderStatus(Connection conn, int orderId) {
        try {
            String query = "Select * from orders where order_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("OrderId : " + resultSet.getString("order_id"));
                System.out.println("CustomerId : " + resultSet.getString("customer_id"));
                System.out.println("Status : " + resultSet.getString("order_status"));
                System.out.println("OrderDate : " + resultSet.getString("order_date"));
                System.out.println("Order Amount : " + resultSet.getString("order_amount"));
            } else {
                throw new OrderNotFoundException("****************The Order was not found************* ");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void displayItems(Connection conn, int customer_id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from  bakeryitems");
            ResultSet rs = ps.executeQuery();
            System.out.println("The bakey items available are :");
            while (rs.next()) {
                int item_id = rs.getInt("item_id");
                String item_name = rs.getString("item_name");
                String item_amount = rs.getString("item_amount");
                System.out.println("Items_Id : " + item_id + " | Items_Name : " + item_name + " | Items_Amount : ₹" + item_amount);
            }
            OrderBakeryItems(conn, customer_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
