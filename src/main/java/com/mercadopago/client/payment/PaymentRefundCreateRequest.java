package com.mercadopago.client.payment;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/** Refund creation request data. */
@Getter
@Builder
public class PaymentRefundCreateRequest {
  /** Amount to be refunded. */
  private final BigDecimal amount;
}
