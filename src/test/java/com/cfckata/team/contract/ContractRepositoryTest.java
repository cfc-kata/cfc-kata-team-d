package com.cfckata.team.contract;

import com.cfckata.common.RepositoryTest;
import com.cfckata.team.contract.dao.ContractRepository;
import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.customer.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Date;

public class ContractRepositoryTest extends RepositoryTest {
    @Autowired
    private ContractRepository contractRepository;

    private String customerId="100001";
    private String idNo="411522198600011234";
    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_success_limit_amt() {
        BigDecimal limAmt  = contractRepository.selectLimitByAge(19);
        Assert.assertEquals(new BigDecimal("10000").compareTo(limAmt)==0,true);
    }

    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_success_select() {
        String id ="10000";
        Contract contract  = contractRepository.selectById(id);
        Assert.assertEquals(contract.getCustomerId(),"10000");
    }

    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_success_save() {
        int i= contractRepository.insert(this.createNormalTestContract());
        Assert.assertEquals(i,1);
    }

    public Contract createNormalTestContract() {
        Contract contract =new Contract();
        contract.setId(System.currentTimeMillis()+"");
        contract.setCustomerId(customerId);
        contract.setTerm(12);
        contract.setAmt(new BigDecimal("200"));
        contract.setDrawAmt(new BigDecimal("0"));
        contract.setStatus("ACTIVE");
        contract.setRate(new BigDecimal("0.44"));
        contract.setOverDate(new Date());
        contract.setCreateTime(new Date());
        contract.setUpdateTime(new Date());
        return contract;

    }

    public CreateContractRequest createNormalTestRequest() {
        CreateContractRequest request =new CreateContractRequest();
        Customer customer =new Customer();
        customer.setId(customerId);
        customer.setIdNumber(idNo);
        customer.setMobilePhone("130xxxxxx");
        customer.setName("zhangsan");
        request.setCustomer(customer);
        request.setApplyAmt(new BigDecimal("200"));
        request.setApplyTerm(12);
        request.setApplyRate(new BigDecimal("0.34"));
        return request;

    }
}
