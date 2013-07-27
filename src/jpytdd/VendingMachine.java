package jpytdd;

import static jpytdd.Cash.Y50;

public class VendingMachine {

    private final Dispenser dispenser;
    private final ChangeDispenser changeDispenser;
    private int count;
    private int price;

    public VendingMachine(Dispenser dispenser, ChangeDispenser changeDispenser) {
        this.dispenser = dispenser;
        this.changeDispenser = changeDispenser;
    }

    public void setStock(int count, int price) {
        this.count = count;
        this.price = price;
    }

    public void acceptPayment(Cash... coins) {
        final int payment = totalFrom(coins);
        final int purchased = payment / price;
        count -= purchased;
        dispenser.dispenseDrink(purchased);


        if (payment != purchased * price) {
            changeDispenser.returns(Y50);
        }
    }

    public int stockCount() {
        return count;
    }

    private int totalFrom(Cash... coins) {
        int total = 0;
        for (Cash coin : coins) {
            total += coin.value;
        }
        return total;
    }
}
