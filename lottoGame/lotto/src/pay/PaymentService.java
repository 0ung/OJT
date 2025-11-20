package pay;


//이거는 싱글톤패턴으로 구성해보세요
public class PaymentService {

    private static PaymentService instance;

    private PaymentService() {}

    public static PaymentService getInstance() {
        if (instance == null) {
            instance = new PaymentService();
        }
        return instance;
    }

    public void pay(Payment payment) {
        System.out.println("\n===== 결제 시작 =====");
        System.out.println(payment);
        try {
            payment.process();
            System.out.println("===== 결제 성공 =====");
        } catch (IllegalArgumentException e) {
            System.out.println("===== 결제 실패 =====");
            System.out.println("에러: " + e.getMessage());
        }
    }
}
