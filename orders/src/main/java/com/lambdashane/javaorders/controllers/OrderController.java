package com.lambdashane.javaorders.controllers;

import com.lambdashane.javaorders.models.Order;
import com.lambdashane.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController
{
    @Autowired
    private OrderServices orderServices;

    //    http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{ordnum}", produces = "application/json")
    public ResponseEntity<?> getOrderById(@PathVariable long ordnum)
    {
        Order o = orderServices.findById(ordnum);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
