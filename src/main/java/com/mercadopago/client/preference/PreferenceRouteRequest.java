package com.mercadopago.client.preference;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

/** Flight information. */
@Getter
@Builder
public class PreferenceRouteRequest {
  /** Departure. */
  private final String departure;

  /** Destination. */
  private final String destination;

  /** Departure date. */
  private final OffsetDateTime departureDateTime;

  /** Arrival date. */
  private final OffsetDateTime arrivalDateTime;

  /** Company. */
  private final String company;
}
