package com.rnd.java21springboot3jpa.user.entity;

import java.math.BigDecimal;

public interface UserOrderSummary {
  Long getUserId();

  BigDecimal getTotalPurchase();
}
