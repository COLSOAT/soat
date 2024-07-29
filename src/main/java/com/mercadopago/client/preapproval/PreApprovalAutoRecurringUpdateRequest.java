package com.mercadopago.client.preapproval;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/** Recurring data. */
@Getter
@Builder
public class PreApprovalAutoRecurringUpdateRequest {
  /** Recurring amount. */
  private final BigDecimal transactionAmount;

  /** Recurring start date. */
  private final OffsetDateTime startDate;

  /** Recurring end date. */
  private final OffsetDateTime endDate;
}
