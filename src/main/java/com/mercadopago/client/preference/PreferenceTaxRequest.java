package com.mercadopago.client.preference;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/** Tax from preference. */
@Getter
@Builder
public class PreferenceTaxRequest {
  /** Tax type. */
  private final String type;

  /** Tax value. */
  private final BigDecimal value;
}
