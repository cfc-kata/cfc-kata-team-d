package com.cfckata.team.contract.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class ContractDO {

    private String  id;

    private String customerId;

    /**授信金额*/
    private BigDecimal amt;

    /**已提款金额*/
    private BigDecimal drawAmt;

    /**费率*/
    private BigDecimal rate;

    /**合同周期，单位月*/
    private int term;

    /**合同状态 ACTIVE*/
    private String status;

    /**合同到期时间*/
    private Date overDate;

    private Date createTime;

    private Date updateTime;

    private int version;

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

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDrawAmt() {
        return drawAmt;
    }

    public void setDrawAmt(BigDecimal drawAmt) {
        this.drawAmt = drawAmt;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
