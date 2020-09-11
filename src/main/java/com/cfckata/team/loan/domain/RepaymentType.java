package com.cfckata.team.loan.domain;

public enum RepaymentType {
	DEBX(0)
	;

    private int value;

    RepaymentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RepaymentType from(int status) {
        for (RepaymentType s : RepaymentType.values()) {
            if (s.value == status) {
                return s;
            }
        }

        throw new IllegalArgumentException("Unknown order status: " + status);
    }
}
