package com.cfckata.team.loan.response;


public class LoanSendResponse {
    private String loanId;

	public LoanSendResponse() {
	}

	public LoanSendResponse(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

   
}
