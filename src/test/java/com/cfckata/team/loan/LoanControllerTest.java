package com.cfckata.team.loan;

import com.cfckata.common.ApiTest;
import com.cfckata.team.loan.domain.RepaymentType;
import com.cfckata.team.loan.request.LoanSendRequest;
import com.cfckata.team.loan.response.LoanQryResponse;
import com.cfckata.team.loan.response.LoanSendResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class LoanControllerTest extends ApiTest {

    @Test
    public void should_qry_loan() {
        String loanId = "123";

        ResponseEntity<LoanQryResponse> responseEntity = this.restTemplate.getForEntity(baseUrl + "/loan/" + loanId, LoanQryResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        LoanQryResponse response = responseEntity.getBody();
        assertThat(response.getLoan()).isNotNull();
        assertThat(response.getLoan().getLoanId()).isEqualTo(loanId);
    }

    @Test
    public void should_send_loan() {
        LoanSendRequest request = new LoanSendRequest();
        request.setApplyAmount(new BigDecimal("100"));
        request.setContractId("123");
        request.setCustomerId("bbb");
        request.setInterestRate(new BigDecimal("0.01"));
        request.setRepaymentBankAccount("2222");
        request.setRepaymentType(RepaymentType.DEBX);
        request.setTotalMonth(12);
        request.setWithdrawBankAccount("ccc");

        ResponseEntity<LoanSendResponse> responseEntity = this.restTemplate.postForEntity(baseUrl + "/loan/sendLoan/", request, LoanSendResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        LoanSendResponse response = responseEntity.getBody();
        assertThat(response.getLoanId()).isEqualTo("123456");
    }
}
