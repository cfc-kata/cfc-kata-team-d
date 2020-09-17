package com.cfckata.team.loan.dao.po;

import java.math.BigDecimal;

import com.github.meixuesong.aggregatepersistence.Versionable;

public class RepaymentPlanDO implements Versionable {
    private String id;
    private String loanId;
    private Integer periodNo;
    private String payableDate;
    private BigDecimal payableAmount;
    private BigDecimal payableInterest;
    private BigDecimal payableCapital;
    private String status;
    private int version;

	@Override
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getPeriodNo() {
		return periodNo;
	}


	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
	}


	public String getPayableDate() {
		return payableDate;
	}


	public void setPayableDate(String payableDate) {
		this.payableDate = payableDate;
	}


	public BigDecimal getPayableAmount() {
		return payableAmount;
	}


	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}


	public BigDecimal getPayableInterest() {
		return payableInterest;
	}


	public void setPayableInterest(BigDecimal payableInterest) {
		this.payableInterest = payableInterest;
	}


	public BigDecimal getPayableCapital() {
		return payableCapital;
	}


	public void setPayableCapital(BigDecimal payableCapital) {
		this.payableCapital = payableCapital;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getLoanId() {
		return loanId;
	}


	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

}
