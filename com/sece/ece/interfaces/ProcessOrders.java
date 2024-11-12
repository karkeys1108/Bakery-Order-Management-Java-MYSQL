package com.sece.ece.interfaces;

import com.sece.ece.entities.Order;

import java.sql.SQLException;

public interface ProcessOrders {
    void placeOrder(Order orders);
    Order checkOrderStatus(int order_id);
    void cancelOrder(int order_id);

}
