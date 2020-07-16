package com.lambdashane.javaorders.services;

import com.lambdashane.javaorders.models.Customer;
import com.lambdashane.javaorders.models.Order;
import com.lambdashane.javaorders.models.Payment;
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
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0)
        {
            custrepos.findById(customer.getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer "+customer.getCustcode()+" not found"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        // OneToMany
        newCustomer.getOrder()
            .clear();
        for (Order o : customer.getOrder())
        {
            Order newOrder = new Order(o.getOrdamount(),
                o.getAdvanceamount(),newCustomer, o.getOrderdescription());
            newCustomer.getOrder()
                .add(newOrder);
        }

        return custrepos.save(newCustomer);
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
