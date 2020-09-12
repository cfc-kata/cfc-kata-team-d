package com.cfckata.team.contract.domain;

import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.customer.Customer;
import com.cfckata.team.exception.ServiceException;
import com.cfckata.team.sales.domain.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractTest {

    @Test
    public void should_succ_checkAndSetTerm_when_term_normal() {
        Contract contract = new Contract();
        int term=24;
        contract.checkAndSetTerm(term);
        assertThat(contract.getTerm()).isEqualTo(term);
    }

    @Test(expected = ServiceException.class)
    public void should_fail_checkAndSetTerm_when_term_more24() {
        Contract contract = new Contract();
        int term=25;
        contract.checkAndSetTerm(term);
        assertThat(contract.getTerm()).isEqualTo(term);
    }

    @Test
    public void should_succ_checkAndSetAmt_when_amt_normal() {
        Contract contract = new Contract();
        BigDecimal applyAmt = new BigDecimal("1000");
       contract.checkAndSetAmt(applyAmt,new BigDecimal("10000"));
        assertThat(contract.getAmt()).isEqualTo(applyAmt);
    }

    @Test(expected = ServiceException.class)
    public void should_fail_checkAndSetAmt_when_term_moreLimit() {
        Contract contract = new Contract();
        BigDecimal applyAmt = new BigDecimal("11000");
        contract.checkAndSetAmt(applyAmt,new BigDecimal("10000"));
        assertThat(contract.getAmt()).isEqualTo(applyAmt);
    }

    @Test
    public void should_succ_checkAndSetRate_when_normal() {
        Contract contract = new Contract();
        BigDecimal applyRate = new BigDecimal("0.45");
        contract.checkAndSetRate(applyRate);
        assertThat(contract.getRate()).isEqualTo(applyRate);
    }

    @Test(expected = ServiceException.class)
    public void should_fail_checkAndSetRate_when_zero() {
        Contract contract = new Contract();
        BigDecimal applyRate = new BigDecimal("0");
        contract.checkAndSetRate(applyRate);
        assertThat(contract.getRate()).isEqualTo(applyRate);
    }

    @Test
    public void should_succ_toResponse_when_normal() {
        Contract contract = new Contract();
        String id="10000";
        contract.setId(id);
        ContractResponse response = contract.toResponse();
        assertThat(response.getContractId()).isEqualTo(id);
    }


}
