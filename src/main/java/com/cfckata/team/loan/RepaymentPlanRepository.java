package com.cfckata.team.loan;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cfckata.team.loan.dao.mapper.RepaymentPlanMapper;
import com.cfckata.team.loan.domain.RepaymentPlan;

@Repository
public class RepaymentPlanRepository {
    private RepaymentPlanMapper mapper;

	public RepaymentPlanRepository(RepaymentPlanMapper mapper) {
		this.mapper = mapper;
	}

	public RepaymentPlan findById(String id) {
		return mapper.selectByPrimaryKey(id);
    }
	
	public List<RepaymentPlan> findByLoanId(String loanId) {
		return mapper.selectByLoanId(loanId);
	}
	
	public int batchInsert(List<RepaymentPlan> plans) {
		int count = 0;
		for(RepaymentPlan plan : plans){
			count += mapper.insert(plan);
		}
		return count;
	}

	public int updateByPrimaryKey(RepaymentPlan record) {
    	return mapper.updateByPrimaryKey(record);
    }

	public int delete(RepaymentPlan orderDO) {
    	return mapper.delete(orderDO);
    }
   
}
