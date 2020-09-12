package com.cfckata.team.customer;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private CustomerDOMapper mapper;

    public CustomerRepository(CustomerDOMapper mapper) {
        this.mapper = mapper;
    }

    public Customer findById(String id) {
        CustomerDO customerDO = mapper.selectByPrimaryKey(id);
        if(customerDO==null){
            throw new RuntimeException(id+",此客户不存在");
        }
        return customerDO.toCustomer();
    }
}
