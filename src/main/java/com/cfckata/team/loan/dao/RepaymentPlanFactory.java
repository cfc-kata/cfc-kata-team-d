package com.cfckata.team.loan.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cfckata.team.loan.RepaymentPlanRepository;
import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.domain.PlanStatus;
import com.cfckata.team.loan.domain.RepaymentPlan;
import com.cfckata.team.utils.DateUtils;

@Service
public class RepaymentPlanFactory {
	
	private RepaymentPlanRepository repository;

	public RepaymentPlanFactory(RepaymentPlanRepository repository) {
		this.repository = repository;
	}

	public List<RepaymentPlan> createPlans(Loan loan) {
		List<RepaymentPlan> plans = repository.findByLoanId(loan.getLoanId());
		if (plans != null && !plans.isEmpty()) {
            throw new IllegalArgumentException("repository had exists.");
        }
		int totalMonth = loan.getTotalMonth();
		plans = new ArrayList<>(totalMonth);
		BigDecimal applyAmount = loan.getApplyAmount();
		BigDecimal interestRate = loan.getInterestRate();
		BigDecimal payableCapital = applyAmount.multiply(interestRate);//应还利息
		BigDecimal payableInterest = applyAmount;//应还本金
		BigDecimal payableAmount = payableCapital.add(payableInterest);//应还总额
		int periodNo = 1;
		for(int i=0;i<totalMonth;i++){
			RepaymentPlan plan = new RepaymentPlan();
			plan.setId(UUID.randomUUID().toString());
			plan.setPeriodNo(periodNo);
			plan.setPayableAmount(payableAmount);
			plan.setPayableCapital(payableCapital);
			plan.setPayableInterest(payableInterest);
			plan.setPayableDate(DateUtils.dateToString(DateUtils.addTime("04", 1), "yyyy-MM-dd"));
			plan.setStatus(PlanStatus.PLAN.getValue());
			plans.add(plan);
			periodNo++;
		}
		
		return plans;
	}

}
