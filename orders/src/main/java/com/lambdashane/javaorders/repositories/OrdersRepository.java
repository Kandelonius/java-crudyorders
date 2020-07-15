package com.lambdashane.javaorders.repositories;

import com.lambdashane.javaorders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Long>
{
}
