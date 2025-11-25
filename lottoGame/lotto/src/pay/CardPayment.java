package pay;

public class CardPayment extends Payment {

    private final CardType cardType;

    public CardPayment(String userId, int amount, CardType cardType) {
        super(userId, amount);
        this.cardType = cardType;
    }

    @Override
    public int validate() {
        super.validate();

        if (amount < 1000) {
            throw new IllegalArgumentException("카드 결제는 1000원 이상 부터 입니다.");
        }
        return 0;
    }

    @Override
    public int calculateFee() {
        return cardType.calculateFee(amount);
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