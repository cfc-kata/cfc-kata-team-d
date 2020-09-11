package com.cfckata.team.repayment.response;

import java.util.Date;

import com.cfckata.team.repayment.domain.Repayment;

public class RepaymentResponse {

    private String id;

    private Date createTime;
    
    private int version;
    
    public RepaymentResponse() {
    }

    public RepaymentResponse(Repayment repayment) {
        this.id = repayment.getId();
        this.createTime = repayment.getCreateTime();
        this.version=repayment.getVersion();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
