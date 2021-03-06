package com.cfckata.team.contract;

import com.cfckata.team.contract.dao.ContractRepository;
import com.cfckata.team.contract.dao.domain.Contract;
import com.cfckata.team.contract.dao.domain.ContractFactory;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.contract.service.ContractService;
import com.cfckata.team.customer.Customer;
import com.cfckata.team.customer.CustomerRepository;
import com.cfckata.team.product.Product;
import com.cfckata.team.sales.OrderRepository;
import com.cfckata.team.sales.OrderService;
import com.cfckata.team.sales.domain.OrderItem;
import com.cfckata.team.sales.domain.OrderStatus;
import com.cfckata.team.sales.domain.SalesOrder;
import com.cfckata.team.sales.proxy.InsufficientBalanceException;
import com.cfckata.team.sales.proxy.PayProxy;
import com.cfckata.team.sales.proxy.TimeoutException;
import com.cfckata.team.sales.request.CheckoutRequest;
import com.github.meixuesong.aggregatepersistence.AggregateFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ContractServiceTest {
    private ContractService service;
    private ContractRepository contractRepository;
    private ContractFactory factory;
    private CustomerRepository customerRepository;
    private BigDecimal applyAmt;
    private String contractId;
    private int  applyTerm;
    private String customerId;
    private String idNo;
    private BigDecimal applyRate=new BigDecimal("0.38");

    private Contract testContract;

    @Before
    public void setUp() throws Exception {
        contractRepository = mock(ContractRepository.class);
        customerRepository = mock(CustomerRepository.class);
        ContractFactory factory=new ContractFactory(contractRepository,customerRepository);
        service = new ContractService(factory,contractRepository);
        contractId = "orderid";
        applyAmt = new BigDecimal("1000");
        applyTerm=12;
        idNo="";
        testContract=this.createNormalTestContract();
    }

    @Test
    public void should_failed_findById() {
        when(contractRepository.selectById(contractId)).thenReturn(this.createNormalTestContract());
        Contract contract = service.findById(contractId);
        Assert.assertNotNull(contract);
    }

    public Contract createNormalTestContract() {
        Contract contract =new Contract();
        contract.setId(contractId);
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
        request.setApplyAmt(applyAmt);
        request.setApplyTerm(applyTerm);
        request.setApplyRate(applyRate);
        return request;

    }



}
