package com.cfckata.team.contract.dao.domain;

import com.cfckata.team.contract.constants.ContractConstant;
import com.cfckata.team.contract.dao.po.ContractDO;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.utils.DateUtils;
import com.github.meixuesong.aggregatepersistence.Versionable;

import java.math.BigDecimal;

public class Contract extends ContractDO implements Versionable {

    public Contract(){
        this.setDrawAmt(BigDecimal.ZERO);
    }
    public void allowAmt(BigDecimal applyAmt,BigDecimal limtAmt){
         if(applyAmt.compareTo(limtAmt)<0){
             throw new RuntimeException("申请金额不能大于限额");
         }
         this.setAmt(applyAmt);
    }

    public void allowTerm(int applyTerm){
        if(applyTerm>ContractConstant.MAX_TERM){
            throw new RuntimeException("贷款期限不能超过"+ContractConstant.MAX_TERM);
        }
        this.setTerm(applyTerm);
        this.setOverDate(DateUtils.addTime("01",applyTerm));
    }

    public ContractResponse toResponse(){
            ContractResponse response =new ContractResponse();
            response.setContractId(this.getId());
            return response;
    }
}
