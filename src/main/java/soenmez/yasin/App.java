package soenmez.yasin;

import java.math.BigDecimal;
import java.util.EnumSet;

/**
 * Hello world!
 *
 */
public class App {
    /*
    Input 1:
        > 1 book at 12.49
        > 1 music CD at 14.99
        > 1 chocolate bar at 0.85
    Input 2:
        > 1 imported box of chocolates at 10.00
        > 1 imported bottle of perfume at 47.50
    Input 3:
        > 1 imported bottle of perfume at 27.99
        > 1 bottle of perfume at 18.99
        > 1 packet of headache pills at 9.75
        > 1 box of imported chocolates at 11.25
     */

    public static void main(String[] args) {
        Tax salesTax = new SalesTax(
                "sales tax",
                new BigDecimal("0.1"),
                EnumSet.of(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL));
        Tax importTax = new ImportTax(
                "import duty",
                new BigDecimal("0.05"),
                EnumSet.of(Origin.LOCAL));

        ShoppingBasket basket = new ShoppingBasket();
        basket.addTaxSystem(salesTax);
        basket.addTaxSystem(importTax);

        Product book = new Product(
                "book",
                ProductType.BOOK ,
                Origin.LOCAL,
                new BigDecimal("12.49"));

        Product musicCD = new Product(
                "music CD",
                ProductType.OTHER,
                Origin.LOCAL,
                new BigDecimal("14.99"));

        Product chocolateBar = new Product(
                "chocolate bar",
                ProductType.FOOD,
                Origin.LOCAL,
                new BigDecimal("0.85"));

        basket.addProduct(book, 1);
        basket.addProduct(musicCD, 1);
        basket.addProduct(chocolateBar, 1);

        basket.printReceipt();

        basket.emptyBasket();
        System.out.println();

        Product impBoxChocolate = new Product(
                "box of chocolates",
                ProductType.FOOD,
                Origin.IMPORTED,
                new BigDecimal("10.00")
        );

        Product impBottlePerfume = new Product(
                "bottle of perfume",
                ProductType.OTHER,
                Origin.IMPORTED,
                new BigDecimal("47.50")
        );

        basket.addProduct(impBoxChocolate, 1);
        basket.addProduct(impBottlePerfume, 1);

        basket.printReceipt();

        basket.emptyBasket();
        System.out.println();

        Product impBottlePerfume2 = new Product(
                "bottle of perfume",
                ProductType.OTHER,
                Origin.IMPORTED,
                new BigDecimal("27.99")
        );

        Product bottlePerfume = new Product(
                "bottle of perfume",
                ProductType.OTHER,
                Origin.LOCAL,
                new BigDecimal("18.99")
        );

        Product packetOfPills = new Product(
                "packet of headache pills",
                ProductType.MEDICAL,
                Origin.LOCAL,
                new BigDecimal("9.75")
        );

        Product impBoxOfChocolates = new Product(
                "box of chocolates",
                ProductType.FOOD,
                Origin.IMPORTED,
                new BigDecimal("11.25")
        );

        basket.addProduct(impBottlePerfume2, 1);
        basket.addProduct(bottlePerfume, 1);
        basket.addProduct(packetOfPills, 1);
        basket.addProduct(impBoxOfChocolates, 1);

        basket.printReceipt();

        basket.emptyBasket();
        System.out.println();
    }
}