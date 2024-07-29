package com.mercadopago.client.preference;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

/** Item information related to the category. */
@Getter
@Builder
public class PreferenceCategoryDescriptorRequest {
  /** Passenger information. */
  private final PreferencePassengerRequest passenger;

  /** Flight information. */
  private final PreferenceRouteRequest route;

  /** Date of event */
  private final OffsetDateTime eventDate;
}
