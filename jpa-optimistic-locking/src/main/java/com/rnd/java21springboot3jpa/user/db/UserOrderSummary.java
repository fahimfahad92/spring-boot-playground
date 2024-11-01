package com.rnd.java21springboot3jpa.user.db;

import java.math.BigDecimal;

public interface UserOrderSummary {
    Long getUserId();
    BigDecimal getTotalPurchase();
}
