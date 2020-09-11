package com.cfckata.team.contract.dao;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.dao.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public class ContractRepository {

    @Autowired
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
        BigDecimal limitAmt =  contractMapper.selectLimitByAge(age);
        if(limitAmt==null || limitAmt.compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException("限额关系未配置");
        }
        return limitAmt;
    }

}
