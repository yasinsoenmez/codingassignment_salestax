package soenmez.yasin;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    ShoppingBasket basket;

    @Test
    @DisplayName("There should be 3 items in the basket.")
    public void testParseShoppingBasket() {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        int expectedBasketSize = 3;

        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);

        App.parseShoppingBasket(input, basket);
        int actualBasketSize = basket.getBasket().size();

        assertEquals(expectedBasketSize, actualBasketSize, "There are 3 items.");
    }

    @Test
    @DisplayName("There should be 2 items in the basket.")
    public void testParseShoppingBasketWithOneFaultyLine() {
        String input = "1 book at 12.49\n" +
                "1 14.99\n" +
                "1 chocolate bar at 0.85";

        int expectedBasketSize = 2;

        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);

        App.parseShoppingBasket(input, basket);
        int actualBasketSize = basket.getBasket().size();

        assertEquals(expectedBasketSize, actualBasketSize, "There are 2 items.");
    }

    @Test
    @DisplayName("Parsing should work correctly.")
    public void testParseAmount() {
        String[] input = {"1", "book", "at", "12.49"};
        int expectedAmount = 1;

        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);

        var actualAmount = App.parseAmount(input);

        assertEquals(expectedAmount, actualAmount, "Parsing correctly.");
    }

    @Test
    @DisplayName("Parsing should throw exception.")
    public void testParseAmountException() {
        String[] input = {"0", "book", "at", "12.49"};
        String expectedException = "Amount must be higher than zero.";

        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);

        Throwable actualException = assertThrows(IllegalArgumentException.class, () -> App.parseAmount(input));

        assertEquals(expectedException, actualException.getMessage(), "Parsing error.");
    }

    @Test
    @DisplayName("Parsing should work correctly.")
    public void testParsePrice() {
        String[] input = {"1", "book", "at", "12.49"};
        var expectedPrice = new BigDecimal("12.49");

        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);

        var actualPrice = App.parsePrice(input);

        assertEquals(expectedPrice, actualPrice, "Parsing correctly.");
    }

    @Test
    @DisplayName("Parsing should throw exception.")
    public void testParsePriceException() {
        String[] input = {"1", "book", "at", "-1.99"};
        String expectedException = "Price must be higher than zero.";

        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);

        Throwable actualException = assertThrows(IllegalArgumentException.class, () -> App.parsePrice(input));

        assertEquals(expectedException, actualException.getMessage(), "Parsing error.");
    }
}