package com.cfckata.team.loan.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cfckata.team.loan.domain.RepaymentPlan;

@Mapper
@Repository
public interface RepaymentPlanMapper {
    int insert(RepaymentPlan record);

    RepaymentPlan selectByPrimaryKey(String id);

    int updateByPrimaryKey(RepaymentPlan record);

    int delete(RepaymentPlan orderDO);
    
    List<RepaymentPlan> selectByLoanId(String loanId);
}
