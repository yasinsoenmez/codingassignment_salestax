package soenmez.yasin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.EnumSet;

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

    @Test
    @DisplayName("The sales tax, total price and basket size should be 0.")
    public void testEmptyBasket() {
        prepareBasketState();
        prepareCorrectOrder();

        var expectedSalesTax = BigDecimal.ZERO;
        var expectedTotalPrice = BigDecimal.ZERO;
        var expectedBasketSize = 0;

        basket.checkout();
        basket.emptyBasket();

        var actualSalesTax = basket.getTotalSalesTax();
        var actualTotalPrice = basket.getTotalPrice();
        var actualBasketSize = basket.getBasket().size();

        assertEquals(expectedSalesTax, actualSalesTax, "Sales tax is 0.");
        assertEquals(expectedTotalPrice, actualTotalPrice, "Price is 0.");
        assertEquals(expectedBasketSize, actualBasketSize, "Price is 0.");
    }

    @Test
    @DisplayName("There should be 1 tax system.")
    public void testAddTaxSystem() {
        basket = new ShoppingBasket();


        var expectedTaxSystems = 1;

        basket.addTaxSystem(new SalesTax(
                "sales tax",
                new BigDecimal("0.1"),
                EnumSet.of(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL)));

        var actualTaxSystems = basket.getTaxes().size();

        assertEquals(expectedTaxSystems, actualTaxSystems, "There is 1 tax system.");
    }

    @Test
    @DisplayName("There should be 1 order.")
    public void testAddOrder() {
        prepareBasketState();

        var expectedBasketSize = 1;

        basket.addOrder(new Product(
                        "chocolate",
                        ProductType.FOOD,
                        Origin.LOCAL,
                        new BigDecimal("10.00")),
                1);

        var actualBasketSize = basket.getBasket().size();

        assertEquals(expectedBasketSize, actualBasketSize, "There is 1 order.");
    }
}