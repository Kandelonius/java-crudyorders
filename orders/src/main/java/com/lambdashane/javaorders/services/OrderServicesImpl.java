package com.lambdashane.javaorders.services;

import com.lambdashane.javaorders.models.Order;
import com.lambdashane.javaorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    private OrdersRepository ordersrepos;

    @Transactional
    @Override
    public Order save(Order order)
    {
        return ordersrepos.save(order);
    }

    @Override
    public Order findById(long id)
    {
        Order o = new Order();

        return ordersrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order "+id+" does not exist!"));
    }
}
