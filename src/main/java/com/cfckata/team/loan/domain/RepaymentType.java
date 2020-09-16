package com.cfckata.team.loan.domain;

public enum RepaymentType {
	DEBX("等额本息"),
	DEBJ("等额本金")
	;

    private String value;

    RepaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RepaymentType from(String type) {
        for (RepaymentType s : RepaymentType.values()) {
            if (type.equals(s.value)) {
                return s;
            }
        }

        throw new IllegalArgumentException("Unknown payment type: " + type);
    }
}
