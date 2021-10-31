package soenmez.yasin;

import java.math.BigDecimal;

public class Order {
    private Product product;
    private int amount;

    public Order(Product order, int number) {
        this.product = order;
        this.amount = number;
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
}