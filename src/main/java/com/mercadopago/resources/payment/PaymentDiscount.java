package com.mercadopago.resources.payment;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/** PaymentDiscount class. */
@Getter
public class PaymentDiscount {

  /** Discount type. */
  private String type;

  /** Discount value. */
  private BigDecimal value;

  /** Discount Limit Date. */
  private LocalDate limitDate;
}
