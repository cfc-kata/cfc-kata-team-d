package com.cfckata.team.loan.domain;

public enum PlanStatus {
    PLAN("计划中"),
    PAID("已还款"),
    OVERDUE("逾期")
	;

    private String value;

    PlanStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PlanStatus from(String status) {
        for (PlanStatus s : PlanStatus.values()) {
            if (status.equals(s.value)) {
                return s;
            }
        }

        throw new IllegalArgumentException("Unknown plan status: " + status);
    }
}
