package com.sece.ece.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Order {
    private int order_id;
    private int customer_id;
    private double order_amount;
    private Timestamp order_date;
    private String order_status;


    public Order(int order_id, int customer_id, double  order_amount, Timestamp order_date, String order_status) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_amount = order_amount;
        this.order_date = order_date;
        this.order_status = order_status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(float order_amount) {
        this.order_amount = order_amount;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "Order[\n" +
                "OrderId = " + order_id +
                "\nCustomerId = " + customer_id +
                "\nOrderAmount = " + order_amount +
                "\nOrderDate = " + order_date +
                "\nOrderStatus = " + order_status +
                "\n]";
    }
}
