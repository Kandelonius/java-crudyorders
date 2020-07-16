package com.lambdashane.javaorders.repositories;

import com.lambdashane.javaorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>
{
}
