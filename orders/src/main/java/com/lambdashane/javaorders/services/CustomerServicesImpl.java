package com.lambdashane.javaorders.services;

import com.lambdashane.javaorders.models.Customer;
import com.lambdashane.javaorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    private CustomersRepository custrepos;

    @Transactional
    @Override
    public Customer save(Customer restaurant)
    {
        return custrepos.save(restaurant);
    }

    @Override
    public List<Customer> findAllCustomers()
    {
        List<Customer> rtnList = new ArrayList<>();

        custrepos.findAll()
            .iterator()
            .forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Override
    public Customer findById(long id)
    {
        Customer c = new Customer();

        return custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer "+id+" does not exist!"));
    }

    @Override
    public Customer findByCustnameIgnoringCase(String custname)
    {
        Customer c = custrepos.findByCustnameIgnoringCase(custname);
        if(c == null)
        {
            throw new EntityNotFoundException("Customer "+custname+" not found!");
        }
        return c;
    }

    @Override
    public List<Customer> findByNameLike(String subname)
    {
        return custrepos.findByCustnameContainingIgnoreCase(subname);
    }

    @Override
    public void delete(long custcode)
    {
        custrepos.findById(custcode)
            .orElseThrow(() -> new EntityNotFoundException("Customer "+custcode+" not found"));
        custrepos.deleteById(custcode);
    }
}
