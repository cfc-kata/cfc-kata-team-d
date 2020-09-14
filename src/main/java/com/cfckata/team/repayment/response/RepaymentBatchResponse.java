/**
 * @title RepaymentBatchResponse.java
 * @author Thinkpad
 * @date 2020年9月14日 上午12:53:54
 */
package com.cfckata.team.repayment.response;

import java.util.List;

/**
 * @Description <描述>
 * @author Thinkpad
 * @date 2020年9月14日 上午12:53:54
 */
public class RepaymentBatchResponse {

    private List<RepaymentCreatedResponse> successItems;

    private List<RepaymentFailedResponse> failedItems;

    public RepaymentBatchResponse() {}

    public RepaymentBatchResponse(List<RepaymentCreatedResponse> successItems,
        List<RepaymentFailedResponse> failedItems) {
        this.successItems = successItems;
        this.failedItems = failedItems;
    }

    public List<RepaymentCreatedResponse> getSuccessItems() {
        return successItems;
    }

    public void setSuccessItems(List<RepaymentCreatedResponse> successItems) {
        this.successItems = successItems;
    }

    public List<RepaymentFailedResponse> getFailedItems() {
        return failedItems;
    }

    public void setFailedItems(List<RepaymentFailedResponse> failedItems) {
        this.failedItems = failedItems;
    }

}
