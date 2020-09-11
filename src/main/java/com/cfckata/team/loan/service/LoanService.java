package com.cfckata.team.loan.service;

import org.springframework.stereotype.Service;

import com.cfckata.team.loan.domain.Loan;
import com.cfckata.team.loan.request.LoanSendRequest;

@Service
public class LoanService {

	public Loan findById(String id) {
    	Loan loan = new Loan();
    	loan.setLoanId("123");
    	return loan;
	}

	public String sendLoan(LoanSendRequest request) {
		return "123456";
	}


}
