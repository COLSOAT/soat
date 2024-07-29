package com.mercadopago.resources.payment;

import lombok.Getter;

import java.math.BigInteger;

/** PaymentBankInfoCollector class. */
@Getter
public class PaymentBankInfoCollector {
  /** Account ID. */
  private BigInteger accountId;

  /** Account long name. */
  private String longName;
}
