package pay;

public class BankTransferPayment extends Payment{
    private final Bank bank;
    private final String accountNumber;
    

    public BankTransferPayment(String userId, int amount, Bank bank, String accountNumber) {
        super(userId, amount);
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    @Override
    public int validate() {
        super.validate();

        if (bank == null) {
            throw new IllegalArgumentException("은행을 기입해주세요");}

        if (accountNumber == null) {
            throw new IllegalArgumentException("계좌를 기입해주세요");
        }

        if (bank.isOverLimit(amount)) {
            throw new IllegalArgumentException(bank.getBankName() + "의 이체 한도는" + bank.getLimitAmount() +" 입니다.");
        }

        return 0;
    }

    @Override public int calculateFee() {
            return bank.getFee();
        }

    @Override
    public void complete () {
        System.out.println("성공적으로 " + amount +"원 이체를 " + accountNumber + "로 완료했습니다.");
    }

    @Override
    public String toString() {
        return "userId=" + userId + "-amount=" + amount + "-bankName=" + bank.getBankName();
    }
}
