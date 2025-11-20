package pay;

public class BankTransferPayment extends Payment{
    private String bankName;

    public BankTransferPayment(String userId, int amount, String bankName) {
        super(userId, amount);
        this.bankName = bankName;
    }

    @Override
    public void validate() {
        // TODO: 계좌 이체 특수 검증 (은행 점검 시간 등)
    }

    @Override
    public int calculateFee() {
        // TODO: 고정 수수료 반환
        return 0;
    }

    @Override
    public void complete() {
        // TODO: 계좌 이체 성공 메시지 출력
    }

    @Override
    public String toString() {
        // TODO: 계좌 이체 정보 출력
        return "";
    }
}
