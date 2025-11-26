package pay;

public class PointPayment  extends Payment {

    private final int availablePoints;

    public PointPayment(String userId, int amount, int availablePoints) {
        super(userId, amount);
        this.availablePoints = availablePoints;
    }

    @Override
    public int validate() {
        super.validate();
        if (availablePoints > 0) {
            System.out.println("현재 이용가능한 포인트는 " + availablePoints + " 입니다.");
        } else {
            System.out.println("현재 이용가능한 포인트가 없습니다.");
        }
        return 0;
    }

    @Override
    public int calculateFee() {
        return 0;
    }

    @Override
    public void complete() {
        System.out.println(availablePoints+"포인트 결제가 완료되었습니다.");
    }

    @Override
    public String toString() {
        return "userId=" + userId + "-amount=" + amount + "-availablePoints=" + availablePoints;
    }
}
