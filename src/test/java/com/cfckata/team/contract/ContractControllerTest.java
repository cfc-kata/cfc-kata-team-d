package com.cfckata.team.contract;

import com.cfckata.common.ApiTest;
import com.cfckata.team.contract.request.CreateContractRequest;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.core.DataResponse;
import com.cfckata.team.customer.Customer;
import com.cfckata.team.sales.domain.OrderStatus;
import com.cfckata.team.sales.domain.SalesOrder;
import com.cfckata.team.sales.request.ChangeOrderRequest;
import com.cfckata.team.sales.request.CheckoutRequest;
import com.cfckata.team.sales.request.CreateOrderRequest;
import com.cfckata.team.sales.request.OrderItemRequest;
import com.cfckata.team.sales.response.OrderResponse;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractControllerTest extends ApiTest {

    private String customerId="C0001";
    private String idNumber="411522198612230001";
    private BigDecimal applyAmt=new BigDecimal("1000");

    /**
     * 成功创建一份合同
     */
    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_succ_create_contract() {
        String contractId = "CA00001";
        CreateContractRequest request = this.createNormalTestRequest(idNumber,applyAmt,12);
        //ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate.postForEntity(baseUrl + "/contracts/create" ,request, DataResponse<ContractResponse>.class);
        ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate
                .exchange(baseUrl + "/contracts/create", HttpMethod.POST,
                        new HttpEntity<CreateContractRequest>(request),
                        new ParameterizedTypeReference<DataResponse<ContractResponse>>() {
                        });
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        DataResponse<ContractResponse> response = responseEntity.getBody();
        assertThat(response.getData().getContractId()).isNotNull();
        assertThat(response.getData().getCustomer().getId()).isEqualTo(customerId);
    }

    /**
     * 创建合同失败，原因申请金额超过限额
     */
    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_fail_create_contract_amt_moreLimit() {
        String contractId = "CA00001";
        CreateContractRequest request = this.createNormalTestRequest(idNumber,new BigDecimal("300000"),12);
        //ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate.postForEntity(baseUrl + "/contracts/create" ,request, DataResponse<ContractResponse>.class);
        ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate
                .exchange(baseUrl + "/contracts/create", HttpMethod.POST,
                        new HttpEntity<CreateContractRequest>(request),
                        new ParameterizedTypeReference<DataResponse<ContractResponse>>() {
                        });
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        DataResponse<ContractResponse> response = responseEntity.getBody();
        assertThat(response.getRetCode()).isEqualTo("1000002");
    }

    /**
     * 创建合同失败，原因贷款期限超过24期
     */
    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_fail_create_contract_term_more24() {
        String contractId = "CA00001";
        CreateContractRequest request = this.createNormalTestRequest(idNumber,new BigDecimal("1000"),25);
        //ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate.postForEntity(baseUrl + "/contracts/create" ,request, DataResponse<ContractResponse>.class);
        ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate
                .exchange(baseUrl + "/contracts/create", HttpMethod.POST,
                        new HttpEntity<CreateContractRequest>(request),
                        new ParameterizedTypeReference<DataResponse<ContractResponse>>() {
                        });
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        DataResponse<ContractResponse> response = responseEntity.getBody();
        assertThat(response.getRetCode()).isEqualTo("1000003");
    }

    /**
     * 查询合同成功
     */
    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_succ_query_contract() {
        String contractId = "10000";
        ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate
                .exchange(baseUrl + "/contracts/"+contractId, HttpMethod.POST,
                        null,
                        new ParameterizedTypeReference<DataResponse<ContractResponse>>() {
                        });

        System.out.println(responseEntity.getStatusCode());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        DataResponse<ContractResponse> response = responseEntity.getBody();
        assertThat(response.getRetCode()).isEqualTo("0000000");
        assertThat(response.getData().getAmt().equals(new BigDecimal(1500)));
    }

    /**
     * 查询合同失败，因为合同号不存在
     */
    @Test
    @Sql(scripts = "classpath:sql/contract-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/contract-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void should_fail_query_contract_no() {
        String contractId = "10001";
        ResponseEntity<DataResponse<ContractResponse>> responseEntity = this.restTemplate
                .exchange(baseUrl + "/contracts/"+contractId, HttpMethod.POST,
                        null,
                        new ParameterizedTypeReference<DataResponse<ContractResponse>>() {
                        });

        System.out.println(responseEntity.getStatusCode());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        DataResponse<ContractResponse> response = responseEntity.getBody();
        assertThat(response.getRetCode()).isEqualTo("1100001");
    }

    public CreateContractRequest createNormalTestRequest(String idNumber,BigDecimal applyAmt,int applyTerm) {
        CreateContractRequest request =new CreateContractRequest();
        Customer customer =new Customer();
        customer.setId(customerId);
        customer.setIdNumber(idNumber);
        customer.setMobilePhone("130xxxxxx");
        customer.setName("zhangsan");
        request.setCustomer(customer);
        request.setApplyAmt(applyAmt);
        request.setApplyTerm(applyTerm);
        request.setApplyRate(new BigDecimal("0.34"));
        return request;

    }

}
