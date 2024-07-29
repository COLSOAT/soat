package com.mercadopago.resources.preference;

import lombok.Getter;

import java.math.BigDecimal;

/** Tax from preference. */
@Getter
public class PreferenceTax {
  /** Tax type. */
  private String type;

  /** Tax value. */
  private BigDecimal value;
}
