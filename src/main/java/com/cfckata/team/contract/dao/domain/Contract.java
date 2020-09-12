package com.cfckata.team.contract.dao.domain;

import com.cfckata.team.contract.constants.ContractConstant;
import com.cfckata.team.contract.dao.po.ContractDO;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.customer.Customer;
import com.cfckata.team.exception.ServiceException;
import com.cfckata.team.utils.DateUtils;
import com.github.meixuesong.aggregatepersistence.Versionable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Contract extends ContractDO implements Versionable {

    private Logger  log = LoggerFactory.getLogger(Contract.class);

    public Contract(){
        this.setDrawAmt(BigDecimal.ZERO);
    }

    private Customer customer;
    /**
     * 检查并设置金额
     * @param applyAmt 申请金额
     * @param limtAmt  风控限额
     */
    public void checkAndSetAmt(BigDecimal applyAmt,BigDecimal limtAmt){
        log.info("申请金额{}，风控限额{}",applyAmt,limtAmt);
         if(applyAmt.compareTo(limtAmt)>0){
             throw new ServiceException("1000002","申请金额不能大于限额");
         }
         this.setAmt(applyAmt);
    }

    /**
     * 检查并设置贷款期限
     * @param applyTerm 贷款周期，单位月
     */
    public void checkAndSetTerm(int applyTerm){
        if(applyTerm<=0){
            throw new RuntimeException("贷款期限不能小于1");
        }
        if(applyTerm>ContractConstant.MAX_TERM){
            throw new ServiceException("1000003","贷款期限不能超过"+ContractConstant.MAX_TERM);
        }
        this.setTerm(applyTerm);
        this.setOverDate(DateUtils.addTime("01",applyTerm));
    }

    public void checkAndSetRate(BigDecimal applyRate){
        if(applyRate==null|| applyRate.compareTo(BigDecimal.ZERO)<=0){
            throw new ServiceException("1000004","申请费率不能为空");
        }
        this.setRate(applyRate);
    }

    /**
     *
     * @return
     */
    public ContractResponse toResponse(){
            ContractResponse response =new ContractResponse();
            response.setContractId(this.getId());
            response.setCustomer(this.getCustomer());
            response.setOverDate(DateUtils.getCurYMD());
            response.setRate(this.getRate());
            response.setStatus(this.getStatus());
            response.setAmt(this.getAmt());
            response.setRepaymentType("DEBX");

            return response;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
