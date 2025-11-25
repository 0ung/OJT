package pay;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Payment {
    protected String userId;
    protected int amount;
    protected LocalDateTime requestTime;

    public Payment(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
        this.requestTime = LocalDateTime.now();
    }

    public void process() {
        validate();
        calculateFee();
        complete();
    }

    public int validate() {
        if (userId == null) throw new IllegalArgumentException("유효하지 않은 사용자 ID입니다.");

        if (amount < 0) throw new IllegalArgumentException("결제 금액은 0보다 커야합니다.");

        return 0;
    }

    public abstract int calculateFee();

    public void complete() {
        System.out.println("결제가 완료되었습니다.");
    }

    @Override
    public String toString() {
        return "userId=" + userId + "-amount=" + amount + "-requestTime=" + requestTime;
    }

    @Override
    public boolean equals(Object o) {
        Payment payment = (Payment) o;
        return Objects.equals(amount, payment.amount) &&
                Objects.equals(userId, payment.userId) &&
                Objects.equals(requestTime, payment.requestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, amount, requestTime);
    }
}
