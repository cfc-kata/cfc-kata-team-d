package com.cfckata.team.loan.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cfckata.team.loan.domain.Loan;

@Mapper
@Repository
public interface LoanMapper {
    int insert(Loan record);

    Loan selectByPrimaryKey(String id);

    int updateByPrimaryKey(Loan record);

    int delete(Loan orderDO);
}
