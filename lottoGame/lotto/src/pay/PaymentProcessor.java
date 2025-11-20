package pay;

import java.util.Scanner;

public class PaymentProcessor {
    public static void main(String[] args) {
        // TODO: 사용자 입력 받고 Payment 객체 생성 후 PaymentService로 전달
        Scanner scanner = new Scanner(System.in);
        PaymentService paymentService = PaymentService.getInstance();

        System.out.println("\n\n========== 결제 시스템 ==========");
        System.out.println("1. 카드 결제");
        System.out.println("2. 계좌이체");
        System.out.println("3. 포인트 결제");
        System.out.print("선택: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("사용자 ID: ");
        String userId = scanner.nextLine();

        System.out.print("결제 금액: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        Payment payment = null;

        try {
            if (choice == 1) {
                System.out.print("카드 타입 (VISA/MASTER): ");
                String cardType = scanner.nextLine().toUpperCase();
                payment = new CardPayment(userId, amount, cardType);
            } else if (choice == 2) {
                System.out.print("은행명: ");
                String bankName = scanner.nextLine();
                payment = new BankTransferPayment(userId, amount, bankName);
            } else if (choice == 3) {
                System.out.print("보유 포인트: ");
                int availablePoints = scanner.nextInt();
                scanner.nextLine();
                payment = new PointPayment(userId, amount, availablePoints);
            } else {
                System.out.println("잘못된 선택입니다.");
                return;
            }

            paymentService.pay(payment);

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }

        scanner.close();
    }
}
