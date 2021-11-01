package soenmez.yasin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<Order> basket;
    private List<Tax> taxes;

    private static final String IMPORTED = "imported ";
    private static final BigDecimal ROUNDING_STEP = new BigDecimal("0.05");

    public ShoppingBasket() {
        this.basket = new ArrayList<Order>();
        this.taxes = new ArrayList<Tax>();
    }

    public void addOrder(Product product, int amount) {
        basket.add(new Order(product, amount));
    }

    public void addTaxSystem(Tax tax) {
        taxes.add(tax);
    }

    private BigDecimal calculateTax(Order order) {
        BigDecimal orderTotal = order.getOrderTotal();
        BigDecimal taxAmount = new BigDecimal("0.00");

        for (Tax tax : taxes) {
            if (tax.isApplicable(order)) {
                BigDecimal taxRounded = customTaxRounding(orderTotal.multiply(tax.getRate()));
                taxAmount = taxAmount.add(taxRounded);
            }
        }
        return taxAmount;
    }

    public void checkout() {
        BigDecimal totalSalesTax = new BigDecimal("0.00");
        BigDecimal total = new BigDecimal("0.00");

        for (Order order : basket) {
            BigDecimal salesTax = calculateTax(order);
            BigDecimal priceWithTax = order.getOrderTotal().add(salesTax);

            totalSalesTax = totalSalesTax.add(salesTax);
            total = total.add(priceWithTax);

            Product product = order.getProduct();
            String productName = (product.getOrigin() == Origin.IMPORTED) ? IMPORTED + product.getName() : product.getName();

            System.out.println(order.getAmount() + " " + productName + ": " + priceWithTax);
        }

        System.out.println("Sales Taxes: " + totalSalesTax);
        System.out.println("Total: " + total);

        emptyBasket();
        System.out.println();
    }

    public void emptyBasket() {
        basket = new ArrayList<Order>();
    }

    private BigDecimal customTaxRounding(BigDecimal amount) {
        BigDecimal divide = amount.divide(ROUNDING_STEP);
        BigDecimal round = divide.setScale(0, RoundingMode.HALF_UP);
        return round.multiply(ROUNDING_STEP);
    }
}