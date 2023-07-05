import java.util.*;

public class ChangeCalculator {
    private static List<Coin> availableCoins = new ArrayList<>();

    static {
        availableCoins.add(new Coin("5 zl", 500, 1));
        availableCoins.add(new Coin("2 zl", 200, 3));
        availableCoins.add(new Coin("1 zl", 100, 5));
        availableCoins.add(new Coin("50 gr", 50, 10));
        availableCoins.add(new Coin("20 gr", 20, 20));
        availableCoins.add(new Coin("10 gr", 10, 200));
        availableCoins.add(new Coin("5 gr", 5, 100));
        availableCoins.add(new Coin("2 gr", 2, 100));
        availableCoins.add(new Coin("1 gr", 1, 10000));
    }

    public static Map<String, Integer> calculateChange(double amount) {
        int amountInGr = (int) (amount * 100);
        Map<String, Integer> change = new LinkedHashMap<>();

        for (Coin coin : availableCoins) {
            int coinValue = coin.getValue();
            int coinCount = coin.getCount();

            if (coinValue <= amountInGr && coinCount > 0) {
                int numCoins = amountInGr / coinValue;
                numCoins = Math.min(numCoins, coinCount);
                change.put(coin.getName(), numCoins);
                amountInGr -= numCoins * coinValue;
                coin.decreaseCount(numCoins);
            }
        }

        if (amountInGr != 0) {
            change.clear();
        }

        return change;
    }

    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Wprowadz kwote do wydania (zloty, grosz): ");
            String amountStr = scanner.nextLine();

            if (amountStr.equals("Wyjscie")) {
                break;
            }

            double amount = parseAmount(amountStr);
            if (amount < 0) {
                System.out.println("Kwota nie wymaga rozmieniania...");
                continue;
            }

            Map<String, Integer> change = calculateChange(amount);
            if (change.isEmpty()) {
                System.out.println("Brak srodkow do wydania reszty");
            } else {
                System.out.println("Wydana reszta: ");
                for (Map.Entry<String, Integer> entry : change.entrySet()) {
                    String coin = entry.getKey();
                    int count = entry.getValue();
                    System.out.println("Wydaj " + count + " monet " + coin);
                }
            }
        }
    }

    private static double parseAmount(String amountStr) {
        try {
            String[] parts = amountStr.split(",");
            int zloty = Integer.parseInt(parts[0]);
            int grosz = Integer.parseInt(parts[1]);
            return zloty + grosz / 100.0;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}