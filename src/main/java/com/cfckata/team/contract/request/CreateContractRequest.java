package com.cfckata.team.contract.request;

import com.cfckata.team.customer.Customer;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateContractRequest implements Serializable {

    private Customer customer;

    private BigDecimal applyRate;

    private String repaymentType;

    private int applyTerm;

    private BigDecimal applyAmt;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getApplyRate() {
        return applyRate;
    }

    public void setApplyRate(BigDecimal applyRate) {
        this.applyRate = applyRate;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public int getApplyTerm() {
        return applyTerm;
    }

    public void setApplyTerm(int applyTerm) {
        this.applyTerm = applyTerm;
    }

    public BigDecimal getApplyAmt() {
        return applyAmt;
    }

    public void setApplyAmt(BigDecimal applyAmt) {
        this.applyAmt = applyAmt;
    }
}
