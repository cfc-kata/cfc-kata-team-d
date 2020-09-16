package com.cfckata.team.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cfckata.team.loan.RepaymentPlanRepository;
import com.cfckata.team.loan.domain.RepaymentPlan;
import com.github.meixuesong.aggregatepersistence.Aggregate;


@Service
public class RepaymentPlanService {

	private RepaymentPlanRepository repaymentPlanRepository;

	public RepaymentPlan findById(String id) {
		Aggregate<RepaymentPlan> aggregate = repaymentPlanRepository.findById(id);
    	return aggregate.getRoot();
	}
	
	public List<RepaymentPlan> findByLoanId(String loanId) {
		return repaymentPlanRepository.findByLoanId(loanId);
	}
	
}
