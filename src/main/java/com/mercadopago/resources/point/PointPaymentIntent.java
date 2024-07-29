package com.mercadopago.resources.point;

import com.mercadopago.net.MPResource;
import lombok.Getter;

import java.math.BigDecimal;

/** Point Payment Intent resource. */
@Getter
public class PointPaymentIntent extends MPResource {
  /** Payment intent identifier. */
  private String id;

  /** Payment intent description. */
  private String description;

  /** Identifier of the device that have a payment intent queued. */
  private String deviceId;

  /** Payment intent amount. */
  private BigDecimal amount;

  /** Payment intent additional info. */
  private PointPaymentIntentAdditionalInfo additionalInfo;

  /** Properties of the payment intent. */
  private PointPaymentIntentPayment payment;
}
