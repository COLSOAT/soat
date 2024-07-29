package com.mercadopago.resources.payment;

import lombok.Getter;

import java.math.BigDecimal;

/** PaymentFeeDetail class. */
@Getter
public class PaymentFeeDetail {
  /** Fee type. */
  private String type;

  /** Who absorbs the cost. */
  private String feePayer;

  /** Fee amount. */
  private BigDecimal amount;
}
