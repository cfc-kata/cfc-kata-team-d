package com.cfckata.team.loan.domain;

public enum LoanStatus {
	INIT("init"),
	PROCESSING("processing"),
	SUCCESS("success"),
	FAILED("failed")
	;

    private String value;

    LoanStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LoanStatus from(String status) {
        for (LoanStatus s : LoanStatus.values()) {
            if (status.equals(s.value)) {
                return s;
            }
        }

        throw new IllegalArgumentException("Unknown order status: " + status);
    }
}
