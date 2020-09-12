package com.cfckata.team.customer;

import com.cfckata.team.utils.DateUtils;

public class Customer {
    private String id;
    private String name;

    private String idNumber;

    private String mobilePhone;


    public Customer() {
    }

    public Customer(String id, String name,String idNumber,String mobilePhone) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.mobilePhone=mobilePhone;
    }


    public int getAge(){
        return Integer.parseInt(DateUtils.getCurTime("yyyyy"))-Integer.parseInt(this.idNumber.substring(6,10));
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
