package com.cfckata.team.repayment.dao;

import java.util.Date;

import com.cfckata.team.repayment.domain.Repayment;

public class RepaymentDO {
    private String id;

    private Date createTime;

    private int version;

    public RepaymentDO() {}

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

    public Repayment toRepayment() {
        Repayment repayment = new Repayment();
        repayment.setId(getId());
        repayment.setCreateTime(getCreateTime());
        repayment.setVersion(getVersion());
        return repayment;
    }

    public RepaymentDO(Repayment repayment) {
        setId(repayment.getId());
        setCreateTime(repayment.getCreateTime());
        setVersion(repayment.getVersion());
    }

}
