package com.mercadopago.client.merchantorder;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

/** Estimated delivery time information. */
@Getter
@Builder
public class MerchantOrderShippingEstimatedDeliveryRequest {
  /** Estimated delivery date. */
  private final OffsetDateTime date;

  /** Estimated lower delivery time. */
  private final String timeFrom;

  /** Estimated upper delivery time. */
  private final String timeTo;
}
