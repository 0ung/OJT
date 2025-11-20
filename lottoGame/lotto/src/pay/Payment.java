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
        // TODO: validate, calculateFee, complete 순서대로 호출
    }

    public void validate() {
        // TODO: 공통 검증 로직 작성
    }

    public abstract int calculateFee(); // TODO: 수수료 계산 로직 자식이 구현

    public void complete() {
        // TODO: 결제 완료 시 출력
    }

    // TODO: toString 재정의
    @Override
    public String toString() {
        return "";
    }

    // TODO: equals 재정의
    @Override
    public boolean equals(Object o) {
        return false;
    }

    // TODO: hashCode 재정의
    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
