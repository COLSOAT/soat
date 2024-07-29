package com.mercadopago.client.payment;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/** PaymentDiscountRequest class. */
@Getter
@Builder
public class PaymentDiscountRequest {

  /** Discount type. */
  private String type;

  /** Discount value. */
  private BigDecimal value;

  /** Discount Limit Date. */
  private LocalDate limitDate;
}
