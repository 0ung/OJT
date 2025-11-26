package pay;

import java.util.Arrays;

public enum Bank {

    KB("국민은행", 0, 10_000_000),
    SHINHAN("신한은행", 0, 10_000_000),
    WOORI("우리은행", 500, 30_000_000),
    HANA("하나은행", 500, 30_000_000),
    NH("농협은행", 0, 20_000_000);

    private final String bankName;
    private final int fee;
    private final int limitAmount;

    Bank(String bankName, int fee, int limitAmount) {
        this.bankName = bankName;
        this.fee = fee;
        this.limitAmount = limitAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public int getFee() {
        return fee;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public boolean isOverLimit(int amount) {
        return amount > limitAmount;
    }
}
