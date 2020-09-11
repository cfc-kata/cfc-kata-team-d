package com.cfckata.team.loan.response;

import com.cfckata.team.loan.domain.Loan;

public class LoanQryResponse {
	
    private Loan loan;

	public LoanQryResponse() {
	}

	public LoanQryResponse(Loan loan) {
		this.loan = loan;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
    
	
   
}
