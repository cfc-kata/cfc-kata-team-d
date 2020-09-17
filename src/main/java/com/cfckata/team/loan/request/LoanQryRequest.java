package com.cfckata.team.loan.request;

public class LoanQryRequest {
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
