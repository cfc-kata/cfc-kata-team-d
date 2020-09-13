package com.cfckata.team.repayment.domain;

import java.util.Date;

import com.github.meixuesong.aggregatepersistence.Versionable;

public class Repayment implements Versionable {

    private String id;

    private Date createTime;
    
    private int version;


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

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
