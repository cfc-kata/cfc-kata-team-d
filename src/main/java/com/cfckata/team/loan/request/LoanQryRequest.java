package com.cfckata.team.loan.request;

import org.springframework.lang.NonNull;

public class LoanQryRequest {
	@NonNull
    private String loanId ;

	public LoanQryRequest(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

   
}
