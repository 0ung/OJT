package pay;

public class BankTransferPayment extends Payment{
    private String bankName;

    public BankTransferPayment(String userId, int amount, String bankName) {
        super(userId, amount);
        this.bankName = bankName;
    }

    @Override
    public void validate() {
        super.validate();

        if (bankName = null) thorw new IllegalArgumentException("은행을 기입해주세요.");
        if (amount >= 10000000) throw new IllegalArgumentException("이체한도 1000만원을 초과했습니다.");

    }

    @Override
    public int calculateFee() {
        return 0;
    }

    @Override
    public void complete() {
        System.out.println("성공적으로 " + amount +"원 이체를 완료했습니다.");
    }

    @Override
    public String toString() {
        return "userId=" + userId + "-amount=" + amount + "-bankName=" + bankName;
    }
}
