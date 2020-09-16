package com.cfckata.team.loan;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.cfckata.team.loan.dao.mapper.RepaymentPlanMapper;
import com.cfckata.team.loan.domain.RepaymentPlan;
import com.github.meixuesong.aggregatepersistence.Aggregate;
import com.github.meixuesong.aggregatepersistence.AggregateFactory;

@Repository
public class RepaymentPlanRepository {
    private RepaymentPlanMapper mapper;

	public RepaymentPlanRepository(RepaymentPlanMapper mapper) {
		this.mapper = mapper;
	}

	public Aggregate<RepaymentPlan> findById(String id) {
		RepaymentPlan plan = mapper.selectByPrimaryKey(id);
        if (plan == null) {
            throw new EntityNotFoundException("plan(" + id + ") not found");
        }

        return AggregateFactory.createAggregate(plan);
    }
	
	public List<RepaymentPlan> findByLoanId(String loanId) {
		return mapper.selectByLoanId(loanId);
	}
	
	public int insert(RepaymentPlan plan) {
		return mapper.insert(plan);
	}

	public int updateByPrimaryKey(RepaymentPlan record) {
    	return mapper.updateByPrimaryKey(record);
    }

	public int delete(RepaymentPlan orderDO) {
    	return mapper.delete(orderDO);
    }
   
}
