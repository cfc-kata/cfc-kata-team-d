package com.cfckata.team.loan.domain;

import java.math.BigDecimal;

public class Loan {
    private String loanId;
    private BigDecimal amount;

    public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
        return amount;
    }
}
