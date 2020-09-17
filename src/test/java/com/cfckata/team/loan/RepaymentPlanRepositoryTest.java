package com.cfckata.team.loan;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cfckata.common.RepositoryTest;
import com.cfckata.team.loan.domain.RepaymentPlan;

public class RepaymentPlanRepositoryTest extends RepositoryTest {

	@Autowired
	private RepaymentPlanRepository repaymentPlanRepository;

	@Test
	public void should_success_save_plans() {
		List<RepaymentPlan> plans = createNomalRepaymentPlan();
		int i = repaymentPlanRepository.batchInsert(plans);
		Assert.assertEquals(12, i);
		String loanId = "loan0003";
		List<RepaymentPlan> descPlans = repaymentPlanRepository.findByLoanId(loanId);
		assertThat(descPlans).isNotEmpty();
		assertThat(descPlans.size()).isEqualTo(12);
	}

	private List<RepaymentPlan> createNomalRepaymentPlan() {
		List<RepaymentPlan> plans = new ArrayList<RepaymentPlan>();
		for (int i = 0; i < 12; i++) {
			int periodNo = i + 1;
			RepaymentPlan testPlan = new RepaymentPlan();
			testPlan.setId("p000" + periodNo);
			testPlan.setLoanId("loan0003");
			testPlan.setPayableAmount(new BigDecimal("11.00"));
			testPlan.setPayableCapital(new BigDecimal("10.00"));
			testPlan.setPayableInterest(new BigDecimal("1.00"));
			testPlan.setPayableDate("");
			testPlan.setPeriodNo(periodNo);
			testPlan.setVersion(1);
			testPlan.setStatus("PLAN");
			plans.add(testPlan);
		}
		return plans;
	}

}
