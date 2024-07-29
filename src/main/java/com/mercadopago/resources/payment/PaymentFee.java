package com.mercadopago.resources.payment;

import lombok.Getter;

import java.math.BigDecimal;

/** PaymentFee class. */
@Getter
public class PaymentFee {

  /** Fee type. */
  private String type;

  /** Fee value. */
  private BigDecimal value;
}
