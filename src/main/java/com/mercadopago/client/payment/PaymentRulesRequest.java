package com.mercadopago.client.payment;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/** PaymentRulesRequest class. */
@Getter
@Builder
public class PaymentRulesRequest {

  /** Discounts. */
  private List<PaymentDiscountRequest> discounts;

  /** Fine. */
  private PaymentFeeRequest fine;

  /** Interest. */
  private PaymentFeeRequest interest;
}
