package com.mercadopago.resources.payment;

import lombok.Getter;

import java.math.BigDecimal;

/** PaymentTax class. */
@Getter
public class PaymentTax {
  /** Tax type. */
  private String type;

  /** Tax value. */
  private BigDecimal value;
}
