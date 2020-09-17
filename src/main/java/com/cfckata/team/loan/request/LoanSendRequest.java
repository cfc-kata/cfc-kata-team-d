package com.cfckata.team.loan.request;

import java.math.BigDecimal;

import com.cfckata.team.loan.domain.RepaymentType;

public class LoanSendRequest {
    private String contractId;
    private BigDecimal applyAmount;
    private Integer totalMonth;
    private BigDecimal interestRate;
    private String withdrawBankAccount;
    private String repaymentBankAccount;
    private RepaymentType repaymentType;
    
    
	public LoanSendRequest() {
		
	}
	public LoanSendRequest(String contractId, BigDecimal applyAmount, Integer totalMonth,
			BigDecimal interestRate, String withdrawBankAccount, String repaymentBankAccount,
			RepaymentType repaymentType) {
		this.contractId = contractId;
		this.applyAmount = applyAmount;
		this.totalMonth = totalMonth;
		this.interestRate = interestRate;
		this.withdrawBankAccount = withdrawBankAccount;
		this.repaymentBankAccount = repaymentBankAccount;
		this.repaymentType = repaymentType;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	public Integer getTotalMonth() {
		return totalMonth;
	}
	public void setTotalMonth(Integer totalMonth) {
		this.totalMonth = totalMonth;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public String getWithdrawBankAccount() {
		return withdrawBankAccount;
	}
	public void setWithdrawBankAccount(String withdrawBankAccount) {
		this.withdrawBankAccount = withdrawBankAccount;
	}
	public String getRepaymentBankAccount() {
		return repaymentBankAccount;
	}
	public void setRepaymentBankAccount(String repaymentBankAccount) {
		this.repaymentBankAccount = repaymentBankAccount;
	}
	public RepaymentType getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(RepaymentType repaymentType) {
		this.repaymentType = repaymentType;
	}

   
}
