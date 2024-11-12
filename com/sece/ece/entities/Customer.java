package com.sece.ece.entities;

public class Customer {
    private int customer_Id;
    private String customer_Name;
    private String customer_phone;
    private String customer_email;
    private String customer_address;


    public Customer(int customer_Id, String customer_Name, String customer_email,String customer_phone, String customer_address) {
        this.customer_Id = customer_Id;
        this.customer_Name = customer_Name;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
        this.customer_address = customer_address;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customer_Id=" + customer_Id +
                ", customer_Name='" + customer_Name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", customer_address='" + customer_address + '\'' +
                '}';
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
}
