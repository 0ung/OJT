package pay;

public enum Bank {

    KB("국민은행", 0, 10000000),
    SHINHAN("신한은행", 0, 1000000),
    WOORI("우리은행", 500, 30000000),
    HANA("하나은행", 500, 30000000),
    NH("농협은행", 0, 20000000);

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

    public static Bank findBank(String name) {
        for (Bank bank : values()) {
            if (bank.bankName.contains(name)) {
                return bank;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 은행입니다.");
    }
}
