package com.cfckata.team.contract.response;

import java.io.Serializable;

public class ContractResponse implements Serializable {

    private String id;

    private String customerId;

    public ContractResponse(){}

    public ContractResponse(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
