package com.lambdashane.javaorders.services;

import com.lambdashane.javaorders.models.Order;

public interface OrderServices
{
    Order save(Order order);

    Order findById(long id);

    void delete(long ordnum);
}
