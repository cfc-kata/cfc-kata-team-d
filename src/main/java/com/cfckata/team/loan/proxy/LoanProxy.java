package com.cfckata.team.loan.proxy;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class LoanProxy {

    public String pay(String bankAccount, BigDecimal applyAmount) {
        //Call exteranl service
    	return "success";
    }
}
