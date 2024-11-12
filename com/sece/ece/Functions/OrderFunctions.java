package com.sece.ece.Functions;

import com.sece.ece.Exceptions.HistoryNotFoundException;
import com.sece.ece.Exceptions.OrderNotFoundException;
import com.sece.ece.entities.Order;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderFunctions {
    public static void showLogHistories(Connection conn, int customerId)
    {
        try{
            String query = "Select * from orders where customer_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,customerId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                System.out.println("Customer Id: "+rs.getString("customer_id"));
                System.out.println("Order Amount : "+rs.getString("order_amount"));
                System.out.println("Order Date : "+rs.getString("order_date"));
                System.out.println("Order Status : "+rs.getString("order_status"));
            }

        }catch (HistoryNotFoundException e)
        {
            String Error = "The History of the customer was not founded ";
            System.out.println(Error);
            System.out.println(e.toString());

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    static void OrderBakeryItems(Connection conn, int customer_id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of items needed :");
        List<Order> orderList = new ArrayList<Order>();
        int count = sc.nextInt();;
        int i = 1;
        double total = 0;
        int total_quantities = 0;
        for(i = 1 ; i <= count ; i++) {
            System.out.println("Enter Product "+i+" ID : ");
            int product_id = sc.nextInt();
            System.out.println("Enter Quantity : ");
            int quantity = sc.nextInt();
            sc.nextLine();

            total_quantities += quantity;

            total = total + returnPrice(conn , product_id)*quantity;
        }
        System.out.println("Total price : "+total);
        System.out.println("For confirmation enter confirm / decline for reject");
        String confirm = sc.nextLine();
        if(confirm.equals("confirm")){
            String order_status = "confirm";
            Timestamp timestamp = Timestamp.from(Instant.now());
            Order orders = new Order(getOrderId(conn) , customer_id , total , timestamp , order_status);
            orderList.add(orders);
            placeorder(conn , orderList );
        }
        else{
            System.out.println("Invalid confirmation");
        }

    }

    private static void placeorder(Connection conn,List<Order> orderList) {
        try{
            String query = "INSERT INTO orders (order_id , customer_id , order_amount , order_date , order_status ) values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderList.get(0).getOrder_id());
            ps.setInt(2, orderList.get(0).getCustomer_id());
            ps.setDouble(3,orderList.get(0).getOrder_amount());
            ps.setTimestamp(4,orderList.get(0).getOrder_date());
            ps.setString(5,orderList.get(0).getOrder_status());
            if( ps.executeUpdate() !=0){
                System.out.println("Your order has been placed successfully");
                Order order = orderList.get(0);
                System.out.println("Your order details is : " +order.toString());
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static int getOrderId(Connection conn) {
        int order_id = 0;
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT count(*) FROM orders ";
            preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order_id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_id+1;
    }

    private static double returnPrice(Connection conn, int product_id) {

        double price = 0 ;
        try{
            String query = "SELECT item_amount from bakeryitems where item_id =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                price = rs.getDouble("item_amount");
            }
            else{
                System.out.println("No such product  id found in our bakery ");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return price;
    }

}

