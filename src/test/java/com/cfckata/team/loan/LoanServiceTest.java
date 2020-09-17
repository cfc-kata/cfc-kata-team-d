package com.cfckata.team.loan;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cfckata.common.SpringServiceTest;
import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.service.ContractService;
import com.cfckata.team.loan.domain.RepaymentType;
import com.cfckata.team.loan.request.LoanSendRequest;
import com.cfckata.team.loan.service.LoanService;
import com.cfckata.team.utils.DateUtils;

public class LoanServiceTest extends SpringServiceTest {
	@Autowired
    private LoanService loanService;
    @MockBean
    private ContractService contractService;
    @MockBean
    private LoanRepository loanRepository;
    @MockBean
    private RepaymentPlanRepository repaymentPlanRepository;
 

	@Test
    public void should_create_loan_success_when_give_a_normal_request() {
        when(contractService.findById(anyString())).thenReturn(createContract());
        when(loanRepository.insert(any())).thenReturn(1);
        when(repaymentPlanRepository.batchInsert(any())).thenReturn(1);
		String loanId = loanService.createLoanAndPlans(createRequest());
		Assert.assertNotNull(loanId);
    }

	private LoanSendRequest createRequest() {
		LoanSendRequest request = new LoanSendRequest();
		request.setApplyAmount(new BigDecimal("1000"));
		request.setContractId("C0001");
		request.setInterestRate(new BigDecimal("0.02"));
		request.setTotalMonth(12);
		request.setRepaymentBankAccount("0001");
		request.setRepaymentType(RepaymentType.DEBX);
		request.setWithdrawBankAccount("0002");
		return request;
	}
   
	private Contract createContract() {
    	Contract contract = new Contract();
    	contract.setId("C0001");
    	contract.setAmt(new BigDecimal("10000"));
    	contract.setOverDate(DateUtils.addTime("02", -2));
		return contract;
	}
}
