package com.cfckata.team.customer;

import com.cfckata.team.utils.DateUtils;

public class Customer {
    private String id;
    private String name;

    private String idNo;

    private String mobilePhone;


    public Customer() {
    }

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getAge(){
        return Integer.parseInt(DateUtils.getCurTime("yyyyy"))-Integer.parseInt(this.idNo.substring(6,10));
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
