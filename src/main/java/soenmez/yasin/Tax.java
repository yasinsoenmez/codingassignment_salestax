package soenmez.yasin;

import java.math.BigDecimal;

public interface Tax {
    String getName();
    BigDecimal getRate();
    boolean isApplicable(Order order);
}