package com.mercadopago.client.payment;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/** PaymentFeeRequest class. */
@Getter
@Builder
public class PaymentFeeRequest {

  /** Fee type. */
  private String type;

  /** Fee value. */
  private BigDecimal value;
}
