package com.mercadopago.client.point;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/** Parameters to create a Point Payment Intent request. */
@Getter
@Builder
public class PointPaymentIntentRequest {
  /** A positive value representing how much to charge. */
  private final BigDecimal amount;

  /** Payment intent description. */
  private final String description;

  /** Properties of the payment intent. */
  private final PointPaymentIntentPaymentRequest payment;

  /** Payment intent additional info. */
  private final PointPaymentIntentAdditionalInfoRequest additionalInfo;
}
