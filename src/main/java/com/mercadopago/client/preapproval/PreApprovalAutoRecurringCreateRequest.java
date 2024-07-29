package com.mercadopago.client.preapproval;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/** Recurring data. */
@Getter
@Builder
public class PreApprovalAutoRecurringCreateRequest {
  /** Currency ID. */
  private final String currencyId;

  /** Recurring amount. */
  private final BigDecimal transactionAmount;

  /** Recurring frequency. */
  private final Integer frequency;

  /** Recurring frequency type (days or months). */
  private final String frequencyType;

  /** Recurring start date. */
  private final OffsetDateTime startDate;

  /** Recurring end date. */
  private final OffsetDateTime endDate;
}
