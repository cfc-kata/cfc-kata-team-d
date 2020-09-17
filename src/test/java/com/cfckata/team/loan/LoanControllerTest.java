package com.cfckata.team.loan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cfckata.common.ApiTest;
import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.service.ContractService;
import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.domain.RepaymentType;
import com.cfckata.team.loan.proxy.LoanProxy;
import com.cfckata.team.loan.request.LoanSendRequest;
import com.cfckata.team.loan.response.LoanQryResponse;
import com.cfckata.team.loan.response.LoanSendResponse;
import com.cfckata.team.utils.DateUtils;

public class LoanControllerTest extends ApiTest {

    @MockBean
    private ContractService contractService;
    @MockBean
    private LoanProxy proxy;
    @MockBean
    private LoanRepository loanRepository;
    @MockBean
    private RepaymentPlanRepository repaymentPlanRepository;

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        when(contractService.findById(anyString())).thenReturn(createContract());
        when(proxy.pay(anyString(), any())).thenReturn("success");
        when(loanRepository.insert(any())).thenReturn(1);
        when(repaymentPlanRepository.batchInsert(any())).thenReturn(1);
        when(loanRepository.findById(anyString())).thenReturn(createLoan());
    }
	
    private Loan createLoan() {
		Loan loan = new Loan();
		loan.setLoanId("loan001");
		loan.setContractId("CA00001");
		return loan;
	}

	private Contract createContract() {
    	Contract contract = new Contract();
    	contract.setId("CA00001");
    	contract.setAmt(new BigDecimal("10000"));
    	contract.setOverDate(DateUtils.addTime("02", -2));
		return contract;
	}

	@Test
    public void should_create_loan() {
        LoanSendRequest request = new LoanSendRequest();
        request.setApplyAmount(new BigDecimal("100"));
        request.setContractId("CA00001");
        request.setInterestRate(new BigDecimal("0.01"));
        request.setRepaymentBankAccount("bankAccount001");
        request.setRepaymentType(RepaymentType.DEBX);
        request.setTotalMonth(12);
        request.setWithdrawBankAccount("bankAccount002");

        ResponseEntity<LoanSendResponse> responseEntity = this.restTemplate.postForEntity(baseUrl + "/loan/sendLoan/", request, LoanSendResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getLoanId()).isNotEmpty();
    }
	
	@Test
	public void should_qry_loan() {
		String loanId = "loan001";
		
		ResponseEntity<LoanQryResponse> response = this.restTemplate.getForEntity(baseUrl + "/loan/" + loanId, LoanQryResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getLoan().getLoanId()).isEqualTo("loan001");
	}
}
