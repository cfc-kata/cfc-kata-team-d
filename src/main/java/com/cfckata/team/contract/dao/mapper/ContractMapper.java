package com.cfckata.team.contract.dao.mapper;

import com.cfckata.team.contract.dao.domain.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface ContractMapper {

    int insert(Contract record);

    Contract selectById(String id);

    int deleteById(String id);

    int updateById(Contract record);

    BigDecimal selectLimitByAge(int age);

}
