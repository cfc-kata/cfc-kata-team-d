package com.cfckata.team.loan.service;

import org.springframework.stereotype.Service;

import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.request.LoanSendRequest;

@Service
public class LoanService {

	public Loan findById(String id) {
    	Loan loan = new Loan();
    	loan.setLoanId(id);
    	return loan;
	}

	public String sendLoan(LoanSendRequest request) {
		String contractId=request.getContractId();
		request.setCustomerId(contractId+"");
		String loanId="123456";
		return loanId;
	}


}
