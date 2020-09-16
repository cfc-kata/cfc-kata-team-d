package com.cfckata.team.loan.dao;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.domain.LoanStatus;
import com.cfckata.team.loan.request.LoanSendRequest;

@Service
public class LoanFactory {
	
	public Loan createLoan(LoanSendRequest request) {
        Loan loan = new Loan();
        loan.setLoanId(UUID.randomUUID().toString());
        loan.setContractId(request.getContractId());
        loan.setApplyAmount(request.getApplyAmount());
        loan.setInterestRate(request.getInterestRate());
        loan.setRepaymentBankAccount(request.getRepaymentBankAccount());
        loan.setRepaymentType(request.getRepaymentType().getValue());
        loan.setTotalMonth(request.getTotalMonth());
        loan.setWithdrawBankAccount(request.getWithdrawBankAccount());
        loan.setStatus(LoanStatus.INIT.getValue());
        return loan;
    }

}
