package com.mercadopago.resources.point;

import com.mercadopago.net.MPResource;
import lombok.Getter;

import java.time.OffsetDateTime;

/** Payment intent status resource. */
@Getter
public class PointStatusPaymentIntent extends MPResource {
  /** Payment intent status. */
  private String status;

  /** Payment intent creation date. */
  private OffsetDateTime createdOn;
}
