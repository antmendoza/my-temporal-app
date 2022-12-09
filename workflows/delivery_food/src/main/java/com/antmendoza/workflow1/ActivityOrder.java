package com.antmendoza.workflow1;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ActivityOrder {

    @ActivityMethod
    void placeOrder();

    @ActivityMethod
    void setOrderAccepted();

    @ActivityMethod
    void setOrderPickedUp();

    @ActivityMethod
    void setOrderDelivered();
}

