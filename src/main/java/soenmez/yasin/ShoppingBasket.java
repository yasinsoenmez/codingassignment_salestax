package soenmez.yasin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<Order> basket;
    private List<Tax> taxes;

    private static final String IMPORTED = "imported ";

    public ShoppingBasket() {
        this.basket = new ArrayList<Order>();
        this.taxes = new ArrayList<Tax>();
    }

    public void addProduct(Product product, int amount) {
        basket.add(new Order(product, amount));
    }

    public void addTaxSystem(Tax tax) {
        taxes.add(tax);
    }

    public List<Order> getBasket() {
        return basket;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }


    private BigDecimal calculateTax(Order order) {
        BigDecimal orderTotal = order.getOrderTotal();
        BigDecimal taxAmount = new BigDecimal("0.00");

        for (Tax tax : taxes) {
            if (tax.isApplicable(order)) {
                taxAmount = taxAmount.add(orderTotal.multiply(tax.getRate()));
            }
        }
        return taxAmount;
    }

    public void printReceipt() {
        BigDecimal totalSalesTax = new BigDecimal("0.00");
        BigDecimal total = new BigDecimal("0.00");

        for (Order order : basket) {
            BigDecimal salesTax = calculateTax(order);
            BigDecimal priceWithTax = order.getOrderTotal().add(salesTax);

            totalSalesTax = totalSalesTax.add(salesTax);
            total = total.add(priceWithTax);

            Product product = order.getProduct();
            String productName = (product.getOrigin() == Origin.IMPORTED) ? IMPORTED + product.getName() : product.getName();

            System.out.println("> " + order.getAmount() + " " + productName + ": " + priceWithTax);
        }

        System.out.println("> Sales Taxes: " + totalSalesTax);
        System.out.println("> Total: " + total);
    }

    public void emptyBasket() {
        basket = new ArrayList<Order>();
    }
}