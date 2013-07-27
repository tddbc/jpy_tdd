package jpytdd;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static jpytdd.Cash.*;
import static junit.framework.Assert.assertEquals;

public class VendingMachineTests {
    @Rule public final JUnitRuleMockery context = new JUnitRuleMockery();
    private final Dispenser dispenser = context.mock(Dispenser.class);
    private final ChangeDispenser changeDispenser = context.mock(ChangeDispenser.class);
    final VendingMachine vendingMachine = new VendingMachine(dispenser, changeDispenser);

    @Test public void machineReturnsDrink_for_correctMoney() {
        context.checking(new Expectations() {{
            oneOf(dispenser).dispenseDrink(1);
        }});

        vendingMachine.setStock(5, 100);
        vendingMachine.acceptPayment(Y100);

        assertEquals("stock count", 4, vendingMachine.stockCount());
    }

    @Test public void machineReturnsDrink_and_50y_change() {
        context.checking(new Expectations() {{
            oneOf(dispenser).dispenseDrink(1);
            oneOf(changeDispenser).returns(Y50);
        }});

        vendingMachine.setStock(5, 100);
        vendingMachine.acceptPayment(Y100, Y50);
        assertEquals("stock count", 4, vendingMachine.stockCount());
    }

    @Test public void machineReturnsDrink_and_60Y_change() {
        context.checking(new Expectations() {{
            oneOf(dispenser).dispenseDrink(1);
            oneOf(changeDispenser).returns(Y50, Y10);
        }});

        vendingMachine.setStock(5, 100);
        vendingMachine.acceptPayment(Y100, Y50, Y10);
        assertEquals("stock count", 4, vendingMachine.stockCount());
    }

}
