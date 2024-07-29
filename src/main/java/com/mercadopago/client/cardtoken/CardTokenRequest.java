package com.mercadopago.client.cardtoken;

import lombok.Builder;
import lombok.Data;

/** Card data used in requests. */
@Builder
@Data
public class CardTokenRequest {

  /** Card number. */
  private String cardNumber;

  /** Expiration month of the card. */
  private Integer expirationMonth;

  /** Expiration year of the card. */
  private Integer expirationYear;

  /** Security code of the card. */
  private String securityCode;

  /** Cardholder's name. */
  private String cardholderName;

  /** Id of the card. (Optional) */
  private String cardId;

  /** Id of the customer. (Optional) */
  private String customerId;
}
