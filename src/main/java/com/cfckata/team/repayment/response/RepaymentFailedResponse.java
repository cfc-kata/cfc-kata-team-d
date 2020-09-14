/**
 * @title RepaymentFailedResponse.java
 * @author Thinkpad
 * @date 2020年9月14日 上午12:55:23
 */
package com.cfckata.team.repayment.response;

import java.math.BigDecimal;

/**
 * @Description <描述>
 * @author Thinkpad
 * @date 2020年9月14日 上午12:55:23
 */
public class RepaymentFailedResponse {

    /**
     * "createdTime": "2020-09-02 12:00:21:300", "contractId": "合同号", "repaymentPlanId": "还款计划行ID", "amount": 366,
     * "repaymentBankAccount": "还款卡号", "errorCode": "ERROR-001", "errorMessage": "余额不足"
     */

    private String createdTime;

    private String contractId;

    private String repaymentPlanId;

    private BigDecimal amount;

    private String repaymentBankAccount;

    private String errorCode;

    private String errorMessage;

    public RepaymentFailedResponse() {}

    public RepaymentFailedResponse(String createdTime, String contractId, String repaymentPlanId, BigDecimal amount,
        String repaymentBankAccount, String errorCode, String errorMessage) {
        this.createdTime = createdTime;
        this.contractId = contractId;
        this.repaymentPlanId = repaymentPlanId;
        this.amount = amount;
        this.repaymentBankAccount = repaymentBankAccount;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRepaymentPlanId() {
        return repaymentPlanId;
    }

    public void setRepaymentPlanId(String repaymentPlanId) {
        this.repaymentPlanId = repaymentPlanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRepaymentBankAccount() {
        return repaymentBankAccount;
    }

    public void setRepaymentBankAccount(String repaymentBankAccount) {
        this.repaymentBankAccount = repaymentBankAccount;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
