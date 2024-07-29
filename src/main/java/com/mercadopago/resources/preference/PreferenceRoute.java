package com.mercadopago.resources.preference;

import lombok.Getter;

import java.time.OffsetDateTime;

/** Flight information. */
@Getter
public class PreferenceRoute {
  /** Departure. */
  private String departure;

  /** Destination. */
  private String destination;

  /** Departure date. */
  private OffsetDateTime departureDateTime;

  /** Arrival date. */
  private OffsetDateTime arrivalDateTime;

  /** Company. */
  private String company;
}
