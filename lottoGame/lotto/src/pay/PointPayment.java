package pay;

public class PointPayment  extends Payment {

    private int availablePoints;

    public PointPayment(String userId, int amount, int availablePoints) {
        super(userId, amount);
        this.availablePoints = availablePoints;
    }

    @Override
    public void validate() {
        // TODO: 포인트 잔액 검증
    }

    @Override
    public int calculateFee() {
        // TODO: 항상 0원
        return 0;
    }

    @Override
    public void complete() {
        // TODO: 포인트 결제 성공 메시지
    }

    @Override
    public String toString() {
        // TODO: 포인트 결제 정보 출력
        return "";
    }
}