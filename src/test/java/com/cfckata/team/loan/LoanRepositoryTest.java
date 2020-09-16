package com.cfckata.team.loan;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.cfckata.common.RepositoryTest;
import com.cfckata.team.loan.domain.Loan;

public class LoanRepositoryTest extends RepositoryTest {

	@Autowired
	private LoanRepository loanRepository;

	@Test
	@Sql(scripts = "classpath:sql/loan-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = "classpath:sql/loan-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void should_success_save() {
		Loan testLoan = createNomalLoan();
		int i = loanRepository.insert(testLoan);
		Assert.assertEquals(1, i);
		String loanId = testLoan.getLoanId();
		Loan descLoan = loanRepository.findById(loanId);
		Assert.assertEquals(testLoan.getLoanId(), descLoan.getLoanId());
	}

	private Loan createNomalLoan() {
		Loan testLoan = new Loan();
		testLoan.setLoanId("loan0003");
		testLoan.setContractId("C0001");
		testLoan.setInterestRate(new BigDecimal("0.10"));
		testLoan.setApplyAmount(new BigDecimal("100"));
		testLoan.setRepaymentBankAccount("BankAccount001");
		testLoan.setStatus("init");
		testLoan.setTotalMonth(12);
		testLoan.setWithdrawBankAccount("BankAccount002");
		testLoan.setRepaymentType("DEBX");
		return testLoan;
	}

}
