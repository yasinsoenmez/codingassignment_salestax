package soenmez.yasin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;

public class ImportTax implements Tax {
    private String name;
    private BigDecimal rate;
    private EnumSet<Origin> taxExceptions;

    public ImportTax(String name, BigDecimal rate, EnumSet<Origin> taxExceptions) {
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
        return !taxExceptions.contains(order.getProduct().getOrigin());
    }
}