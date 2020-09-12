package com.cfckata.team.contract.response;

import com.cfckata.team.core.DataResponse;
import com.cfckata.team.customer.Customer;

import java.math.BigDecimal;
import java.util.Date;

public class ContractResponse {

    private String contractId;

    private Customer customer;

    private BigDecimal rate;

    private String repaymentType;

    private Date overDate;

    private String  status;

    private BigDecimal amt;


    public ContractResponse(){}




    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}
