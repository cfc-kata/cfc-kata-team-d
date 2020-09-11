package com.cfckata.team.contract.response;

import com.cfckata.team.customer.Customer;
import com.github.meixuesong.aggregatepersistence.Versionable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContractResponse implements Serializable, Versionable {

    private String contractId;

    private Customer customer;

    private BigDecimal rate;

    private String repaymentType;
    private Date overDate;

    private String  status;

    public ContractResponse(){}

    public ContractResponse(String contractId) {
        this.contractId = contractId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Override
    public int getVersion() {
        return 0;
    }
}
