package soenmez.yasin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<Order> basket;
    private List<Tax> taxes;

    private BigDecimal totalSalesTax;
    private BigDecimal totalPrice;

    private static final String IMPORTED = "imported ";
    private static final BigDecimal ROUNDING_STEP = new BigDecimal("0.05");

    public ShoppingBasket() {
        this.basket = new ArrayList<Order>();
        this.taxes = new ArrayList<Tax>();
        totalSalesTax = BigDecimal.ZERO;
        totalPrice = BigDecimal.ZERO;
    }

    public void addOrder(Product product, int amount) {
        basket.add(new Order(product, amount));
    }

    public void addTaxSystem(Tax tax) {
        taxes.add(tax);
    }

    private BigDecimal calculateTax(Order order) {
        var orderTotal = order.getOrderTotal();
        var taxAmount = BigDecimal.ZERO;

        for (Tax tax : taxes) {
            if (tax.isApplicable(order)) {
                var taxRounded = customTaxRounding(orderTotal.multiply(tax.getRate()));
                taxAmount = taxAmount.add(taxRounded);
            }
        }
        return taxAmount;
    }

    public void checkout() {
        for (Order order : basket) {
            var salesTax = calculateTax(order);
            var priceWithTax = order.getOrderTotal().add(salesTax);
            order.setTotalPriceWithTax(priceWithTax);

            totalSalesTax = totalSalesTax.add(salesTax);
            totalPrice = totalPrice.add(priceWithTax);
        }
    }

    public void printReceipt() {
        for (Order order : basket) {
            var product = order.getProduct();
            var productName = (product.getOrigin() == Origin.IMPORTED) ? IMPORTED + product.getName() : product.getName();
            System.out.println(order.getAmount() + " " + productName + ": " + order.getTotalPriceWithTax());
        }

        System.out.println("Sales Taxes: " + totalSalesTax);
        System.out.println("Total: " + totalPrice);
        System.out.println();
    }

    public void emptyBasket() {
        basket = new ArrayList<Order>();
        totalSalesTax = BigDecimal.ZERO;
        totalPrice = BigDecimal.ZERO;
    }

    static private BigDecimal customTaxRounding(BigDecimal amount) {
        return amount
                .divide(ROUNDING_STEP)
                .setScale(0, RoundingMode.UP)
                .multiply(ROUNDING_STEP);
    }

    public List<Order> getBasket() {
        return basket;
    }

    public BigDecimal getTotalSalesTax() {
        return totalSalesTax;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }
}