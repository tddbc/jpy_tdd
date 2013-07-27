package jpytdd;

public enum Cash {
    Y100(100),
    Y50(50),
    Y10(10);

    public final int value;

    Cash(int value) {
        this.value = value;
    }
}
