package com.cfckata.team.contract.dao;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.dao.mapper.ContractMapper;
import com.cfckata.team.customer.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public class ContractRepository {

    private ContractMapper contractMapper;



    public ContractRepository(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }

    public int insert(Contract contract){
        contract.setCreateTime(new Date());
        contract.setUpdateTime(new Date());
        return this.contractMapper.insert(contract);
    }

    public Contract selectById(String id){
        return contractMapper.selectById(id);
    }

    public int deleteById(String id){
        return contractMapper.deleteById(id);
    }

    public int updateById(Contract contract ){
        return contractMapper.updateById(contract);
    }

    public BigDecimal selectLimitByAge(int age){
        return contractMapper.selectLimitByAge(age);
    }

}
