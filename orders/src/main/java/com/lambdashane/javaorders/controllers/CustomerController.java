package com.lambdashane.javaorders.controllers;

import com.lambdashane.javaorders.models.Customer;
import com.lambdashane.javaorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController
{
    @Autowired
    private CustomerServices customerServices;

    //    http://localhost:2019/customers/orders
    @GetMapping(value = "/orders",
        produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customer> myCustomers = customerServices.findAllCustomers();
        return new ResponseEntity<>(myCustomers,
            HttpStatus.OK);
    }

    //    http://localhost:2019/customers/customer/7
    //    http://localhost:2019/customers/customer/77
    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode)
    {
        Customer c = customerServices.findById(custcode);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/namelike/mes
    //    http://localhost:2019/customers/namelike/cin
    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> findByNameLike(@PathVariable String subname)
    {
        List<Customer> nameList = customerServices.findByNameLike(subname);
        return new ResponseEntity<>(nameList, HttpStatus.OK);
    }



}
