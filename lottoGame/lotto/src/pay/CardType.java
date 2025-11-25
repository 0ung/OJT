package pay;

public enum CardType {
    VISA(0.01),
    MASTER(0.05);

    private final double feeRate;

    CardType(double feeRate) {
        this.feeRate = feeRate;
    }

    public int calculateFee(int amount) {
        return (int) (amount * feeRate);
    }
}
