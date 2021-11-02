package soenmez.yasin;

import java.math.BigDecimal;

public class Product {

    private String name;
    private ProductType type;
    private Origin origin;
    private BigDecimal price;

    public Product(String name, ProductType type, Origin origin, BigDecimal price) {
        if (name == null || type == null || origin == null || price == null) {
            throw new IllegalArgumentException("Null is not accepted.");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be higher than zero.");
        }
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public Origin getOrigin() {
        return origin;
    }

    public BigDecimal getPrice() {
        return price;
    }

}