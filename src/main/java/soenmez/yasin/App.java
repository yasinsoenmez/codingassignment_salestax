package soenmez.yasin;

import java.math.BigDecimal;

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
        Tax salesTax = new Tax("sales tax", new BigDecimal("0.1"));
        Tax importTax = new Tax("import duty", new BigDecimal("0.05"));

        Product book = new Product("book", ProductType.BOOK ,false, new BigDecimal("12.49"));
        Product musicCD = new Product("music CD", ProductType.OTHER, false, new BigDecimal("14.99"));
        Product chocolateBar = new Product("chocolate bar", ProductType.FOOD, false, new BigDecimal("0.85"));

        ShoppingBasket basket = new ShoppingBasket();
        basket.addTaxSystem(salesTax);
        basket.addTaxSystem(importTax);

        basket.addProduct(book, 1);
        basket.addProduct(musicCD, 1);
        basket.addProduct(chocolateBar, 2);


        System.out.println("Hello World!");
        for (Order order : basket.getBasket()) {
            System.out.println(order.getProduct().getName() + ": " + order.getProduct().getPrice());
        }

        basket.printReceipt();
    }
}