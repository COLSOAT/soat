package com.mercadopago.client.payment;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/** PaymentCaptureRequest class. */
@Getter
@Builder
public class PaymentCaptureRequest {
  /** Capture true. */
  private final boolean capture = true;

  /** The amount to capture. */
  private final BigDecimal transactionAmount;
}
