public class Coin {
    private String name;
    private int value;
    private int count;

    public Coin(String name, int value, int count) {
        this.name = name;
        this.value = value;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public void decreaseCount(int numCoins) {
        count -= numCoins;
    }
}