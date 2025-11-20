package pay;

public class CardPayment extends Payment {
    
    //가능하면 Enum도 사용해보세요
    private String cardType; // 예: VISA, MASTER 등

    public CardPayment(String userId, int amount, String cardType) {
        super(userId, amount);
        this.cardType = cardType;
    }

    @Override
    public void validate() {
        // TODO: 카드 결제만의 검증 (한도 등)
    }

    @Override
    public int calculateFee() {
        // TODO: 카드 타입에 따라 수수료 계산
        return 0;
    }

    @Override
    public void complete() {
        // TODO: 카드 결제 성공 메시지 출력
    }

    @Override
    public String toString() {
        // TODO: 카드 결제 정보 출력
        return "";
    }
}