package soenmez.yasin;

import java.math.BigDecimal;

public class Tax {
    private String name;
    private BigDecimal rate;

    public Tax(String name, BigDecimal rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }
}