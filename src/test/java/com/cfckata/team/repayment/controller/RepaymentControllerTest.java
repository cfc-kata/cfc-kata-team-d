package com.cfckata.team.repayment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.cfckata.common.ApiTest;
import com.cfckata.team.repayment.response.RepaymentResponse;

public class RepaymentControllerTest extends ApiTest {

    @Test
    @Sql(scripts = "classpath:sql/repayment-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/repayment-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void SHOULD_FIND_REPAYMENT_WHEN_GIVE_ID() {

        // GIVER
        String repaymentId = "TEST_REPAYMENT";
        // WHEN
        ResponseEntity<RepaymentResponse> responseEntity =
            this.restTemplate.getForEntity(baseUrl + "/repayment/" + repaymentId, RepaymentResponse.class);

        // THEN
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        RepaymentResponse repaymentResponse = responseEntity.getBody();
        assertTrue(repaymentResponse.getId().equals(repaymentId));
    }
}
