package com.lambdashane.javaorders.repositories;

import com.lambdashane.javaorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long>
{

}
