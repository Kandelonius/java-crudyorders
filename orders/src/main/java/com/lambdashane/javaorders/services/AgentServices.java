package com.lambdashane.javaorders.services;

import com.lambdashane.javaorders.models.Agent;

public interface AgentServices
{
    Agent save(Agent agent);

    Agent findById(long id);
}
