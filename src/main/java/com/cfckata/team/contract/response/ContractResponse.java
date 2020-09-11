package com.cfckata.team.contract.response;

import com.github.meixuesong.aggregatepersistence.Versionable;

import java.io.Serializable;

public class ContractResponse implements Serializable, Versionable {

    private String contractId;

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
