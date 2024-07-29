package com.mercadopago.client.payment;

import lombok.Builder;
import lombok.Getter;

import static com.mercadopago.resources.payment.PaymentStatus.CANCELLED;

/** PaymentCancelRequest class. */
@Getter
@Builder
public class PaymentCancelRequest {
  /** Status cancelled. */
  private final String status = CANCELLED;
}
