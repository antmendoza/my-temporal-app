package com.antmendoza.workflow1;

public class ActivityOrderImpl implements ActivityOrder {
    @Override
    public void placeOrder() {
        System.out.println("***** Order has been placed");
    }

    @Override
    public void setOrderAccepted() {
        System.out.println("***** Restaurant has accepted your order");
    }

    @Override
    public void setOrderPickedUp() {
        System.out.println("***** Order has been picked up");
    }

    @Override
    public void setOrderDelivered() {
        System.out.println("***** Order Delivered");
    }

}