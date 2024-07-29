package com.mercadopago.resources.payment;

import lombok.Getter;

import java.util.List;

/** PaymentRules class. */
@Getter
public class PaymentRules {

  /** Discounts. */
  private List<PaymentDiscount> discounts;

  /** Fine. */
  private PaymentFee fine;

  /** Interest. */
  private PaymentFee interest;
}
