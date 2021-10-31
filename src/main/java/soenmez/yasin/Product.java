package soenmez.yasin;

import java.math.BigDecimal;

public class Product {

    public enum ProductType {
        BOOK,
        FOOD,
        MEDICAL,
        OTHER
    }

    private String name;
    private ProductType type;
    private boolean imported;
    private BigDecimal price;

    public Product(String name, ProductType type, boolean imported, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.imported = imported;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public boolean isImported() {
        return imported;
    }

    public BigDecimal getPrice() {
        return price;
    }
}