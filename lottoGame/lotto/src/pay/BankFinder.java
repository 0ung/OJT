package pay;

import java.util.Arrays;

public class BankFinder {

    private BankFinder () {};

    public static Bank findBank(String name) {
        return Arrays.stream(Bank.values())
                .filter(bank -> bank.getBankName().contains(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 은행입니다."));
    }
}
