package com.cfckata.team.sales.contract;

import com.cfckata.common.ApiTest;
import com.cfckata.team.contract.response.ContractResponse;
import com.cfckata.team.sales.domain.OrderStatus;
import com.cfckata.team.sales.domain.SalesOrder;
import com.cfckata.team.sales.request.ChangeOrderRequest;
import com.cfckata.team.sales.request.CheckoutRequest;
import com.cfckata.team.sales.request.CreateOrderRequest;
import com.cfckata.team.sales.request.OrderItemRequest;
import com.cfckata.team.sales.response.OrderResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractControllerTest extends ApiTest {

    @Test
    public void should_query_contract() {
        String customerId = "TEST_USER_ID";
        String contractId = "TEST_CONTRACT";

        ResponseEntity<ContractResponse> responseEntity = this.restTemplate.getForEntity(baseUrl + "/contracts/" + contractId, ContractResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        ContractResponse contract = responseEntity.getBody();
        assertThat(contract.getId()).isEqualTo(contractId);
        assertThat(contract.getCustomerId()).isEqualTo(customerId);
    }

}
