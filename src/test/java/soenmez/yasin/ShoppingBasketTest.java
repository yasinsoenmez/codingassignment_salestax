package soenmez.yasin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingBasketTest {

    ShoppingBasket basket;

    public void prepareBasketState() {
        basket = new ShoppingBasket();
        App.setUpTaxSystem(basket);
    }

    public void prepareCorrectOrder() {
        basket.addOrder(
                new Product(
                        "chocolate",
                        ProductType.FOOD,
                        Origin.LOCAL,
                        new BigDecimal("10.00")),
                1);
        basket.addOrder(
                new Product(
                        "pills",
                        ProductType.MEDICAL,
                        Origin.IMPORTED,
                        new BigDecimal("5.00")),
                1);
        basket.addOrder(
                new Product(
                        "magazine",
                        ProductType.OTHER,
                        Origin.LOCAL,
                        new BigDecimal("6.00")),
                1);
        basket.addOrder(
                new Product(
                        "magazine",
                        ProductType.OTHER,
                        Origin.IMPORTED,
                        new BigDecimal("10.00")),
                1);
    }


    @Test
    @DisplayName("The sales tax should be 2.35 and total price should be 33.35.")
    public void testCalculateTax() {
        prepareBasketState();
        prepareCorrectOrder();

        var expectedSalesTax = new BigDecimal("2.35");
        var expectedTotalPrice = new BigDecimal("33.35");

        basket.checkout();
        var actualSalesTax = basket.getTotalSalesTax();
        var actualTotalPrice = basket.getTotalPrice();

        assertEquals(expectedSalesTax, actualSalesTax, "Sales tax is 2.35.");
        assertEquals(expectedTotalPrice, actualTotalPrice, "Price is 33.35.");
    }
}