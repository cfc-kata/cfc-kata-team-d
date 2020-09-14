/**
 * @title RepaymentCreatedResponse.java
 * @author Thinkpad
 * @date 2020年9月13日 下午11:21:25
 */
package com.cfckata.team.repayment.response;

import java.math.BigDecimal;

/**
 * @Description <描述>
 * @author Thinkpad
 * @date 2020年9月13日 下午11:21:25
 */
public class RepaymentCreatedResponse {

    /**
     * "repaymentId": "扣款流水号", "createdTime": "2020-09-02 12:00:21:300", "loanId": "借据号", "repaymentPlanId": "还款计划行ID",
     * "amount": 366, "repaymentBankAccount": "还款卡号"
     */

    private String repaymentId;

    private String createdTime;

    private String loanId;

    private String repaymentPlanId;

    private BigDecimal amount;

    private String repaymentBankAccount;

    public RepaymentCreatedResponse() {}

    public RepaymentCreatedResponse(String repaymentId, String createdTime, String loanId, String repaymentPlanId,
        BigDecimal amount, String repaymentBankAccount) {
        this.repaymentId = repaymentId;
        this.createdTime = createdTime;
        this.loanId = loanId;
        this.repaymentPlanId = repaymentPlanId;
        this.amount = amount;
        this.repaymentBankAccount = repaymentBankAccount;
    }

    public String getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(String repaymentId) {
        this.repaymentId = repaymentId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
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
}
