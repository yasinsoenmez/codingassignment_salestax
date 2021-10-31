package soenmez.yasin;

import java.math.BigDecimal;
import java.util.EnumSet;

public class SalesTax implements Tax {
    private String name;
    private BigDecimal rate;
    private EnumSet<ProductType> taxExceptions;

    public SalesTax(String name, BigDecimal rate, EnumSet<ProductType> taxExceptions) {
        this.name = name;
        this.rate = rate;
        this.taxExceptions = taxExceptions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public boolean isApplicable(Order order) {
        return !taxExceptions.contains(order.getProduct().getType());
    }
}