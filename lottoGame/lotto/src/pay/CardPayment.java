package pay;

import java.util.Objects;

public class CardPayment extends Payment {
    
    //가능하면 Enum도 사용해보세요
    private String cardType; // 예: VISA, MASTER 등

    public CardPayment(String userId, int amount, String cardType) {
        super(userId, amount);
        this.cardType = cardType;
    }

    @Override
    public void validate() {
        super.validate();

        if (amount < 1000) {
            throw new IllegalArgumentException("카드 결제는 1000원 이상 부터 입니다.");
        }

        if (!Objects.equals(cardType, "VISA") && !Objects.equals(cardType, "MASTER")) {
            throw new IllegalArgumentException("지원하지 않는 카드입니다.");
        }
    }

    @Override
    public int calculateFee() {
        if (Objects.equals(cardType, "VISA")) {
            return (int) (amount * 0.01);
        } else if (Objects.equals(cardType, "MASTER")){
            return (int) (amount * 0.05);
        }
        return 0;
    }

    @Override
    public void complete() {
        System.out.println(cardType+"카드로 "+ amount +"원 결제되었습니다.");
    }

    @Override
    public String toString() {
        return "userId=" + userId + "-amount=" + amount + "-cardType=" + cardType;
    }
}