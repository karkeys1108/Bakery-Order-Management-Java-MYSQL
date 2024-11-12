package com.sece.ece.entities;

public class Pickup {
   private int pickup_id  ;
   private String pickup_address ;
   private String order_id;


    public int getPickup_id() {
        return pickup_id;
    }

    public void setPickup_id(int pickup_id) {
        this.pickup_id = pickup_id;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
