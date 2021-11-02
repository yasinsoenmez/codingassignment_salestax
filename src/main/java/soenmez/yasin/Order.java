package soenmez.yasin;

import java.math.BigDecimal;

public class Order {
    private Product product;
    private int amount;
    private BigDecimal totalPriceWithTax;

    public Order(Product order, int amount) {
        if (order == null) {
            throw new IllegalArgumentException("Null is not accepted.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be higher than zero.");
        }
        this.product = order;
        this.amount = amount;
        totalPriceWithTax = BigDecimal.ZERO;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getOrderTotal() {
        return product.getPrice().multiply(new BigDecimal(amount));
    }

    public BigDecimal getTotalPriceWithTax() {
        return totalPriceWithTax;
    }

    public void setTotalPriceWithTax(BigDecimal totalPriceWithTax) {
        if (totalPriceWithTax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be higher than zero.");
        }
        this.totalPriceWithTax = totalPriceWithTax;
    }
}