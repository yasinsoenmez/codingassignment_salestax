package soenmez.yasin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

    public static final String IMPORTED = "imported";
    public static final List<String> FOOD = List.of("chocolate", "chocolates");
    public static final List<String> MEDICINE = List.of("pill", "pills");
    public static final List<String> BOOK = List.of("book", "books");
    public static final String AT = "at";
    public static final String INSTRUCTIONS = "Type in an order. Checkout with ENTER on blank line. " +
                                              "Exit with ENTER on empty order.";

    public static void main(String[] args) {

        // initialize shopping basket
        ShoppingBasket basket = new ShoppingBasket();
        setUpTaxSystem(basket);

        exampleInput(basket);

        Scanner scanner = new Scanner(System.in);
        StringBuffer orders = new StringBuffer();

        System.out.println(INSTRUCTIONS);

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                if (orders.length() == 0) {
                    break;
                }

                parseShoppingBasket(orders.toString(), basket);
                basket.checkout();
                orders.setLength(0);
                System.out.println(INSTRUCTIONS);
            } else {
                orders.append(line).append('\n');
            }
        }
    }

    public static void setUpTaxSystem(ShoppingBasket basket) {
        // Setting up tax system
        Tax salesTax = new SalesTax(
                "sales tax",
                new BigDecimal("0.1"),
                EnumSet.of(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL));

        Tax importTax = new ImportTax(
                "import duty",
                new BigDecimal("0.05"),
                EnumSet.of(Origin.LOCAL));

        basket.addTaxSystem(salesTax);
        basket.addTaxSystem(importTax);
    }

    public static void parseShoppingBasket(String input, ShoppingBasket basket) {
        var lines = input.lines().collect(Collectors.toList());

        for (String line : lines) {
            var words = line.split(" ");

            try {
                var product = parseProduct(words);
                var amount = parseAmount(words);

                basket.addOrder(product, amount);
            } catch (Exception e) {
                System.out.println("Error parsing order: " + line);
            }
        }
    }

    public static Product parseProduct(String[] input)  {
        var price = parsePrice(input);
        var origin = parseOrigin(input);
        var name = parseProductName(input);
        var productType = parseProductType(input);

        return new Product(
                name,
                productType,
                origin,
                price
        );
    }

    public static String parseProductName(String[] input)  {
        return Arrays.stream(input)
                .limit(input.length - 1)
                .skip(1)
                .filter(s -> !s.equals(IMPORTED))
                .filter(s -> !s.equals(AT))
                .collect(Collectors.joining(" "));
    }

    public static int parseAmount(String[] input)  {
        var amount = Integer.parseInt(input[0]);

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be higher than zero.");
        }

        return amount;
    }

    public static BigDecimal parsePrice(String[] input)  {
        var price = new BigDecimal(input[input.length - 1]);

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be higher than zero.");
        }

        return price;
    }

    public static Origin parseOrigin(String[] input) {
        return Arrays.asList(input).contains(IMPORTED) ? Origin.IMPORTED : Origin.LOCAL;
    }

    public static ProductType parseProductType(String[] input) {
        ProductType productType;

        if (Arrays.stream(input).anyMatch(FOOD::contains)) {
            productType = ProductType.FOOD;
        } else if (Arrays.stream(input).anyMatch(MEDICINE::contains)) {
            productType = ProductType.MEDICAL;
        } else if (Arrays.stream(input).anyMatch(BOOK::contains)) {
            productType = ProductType.BOOK;
        } else {
            productType = ProductType.OTHER;
        }

        return productType;
    }

    public static void exampleInput(ShoppingBasket basket) {

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
        String input = "1 book at 12.49\n" +
                       "1 music CD at 14.99\n" +
                       "1 chocolate bar at 0.85";

        parseShoppingBasket(input, basket);
        basket.checkout();

        input = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";

        parseShoppingBasket(input, basket);
        basket.checkout();

        input = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "1 box of imported chocolates at 11.25";

        parseShoppingBasket(input, basket);
        basket.checkout();

        /*Product book = new Product(
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

        basket.addOrder(book, 1);
        basket.addOrder(musicCD, 1);
        basket.addOrder(chocolateBar, 1);

        basket.checkout();

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

        basket.addOrder(impBoxChocolate, 1);
        basket.addOrder(impBottlePerfume, 1);

        basket.checkout();

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

        basket.addOrder(impBottlePerfume2, 1);
        basket.addOrder(bottlePerfume, 1);
        basket.addOrder(packetOfPills, 1);
        basket.addOrder(impBoxOfChocolates, 1);

        basket.checkout();*/
    }
}