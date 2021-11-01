package soenmez.yasin;

import java.math.BigDecimal;

public class Product {

    private String name;
    private ProductType type;
    private Origin origin;
    private BigDecimal price;

    public Product(String name, ProductType type, Origin origin, BigDecimal price) {
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