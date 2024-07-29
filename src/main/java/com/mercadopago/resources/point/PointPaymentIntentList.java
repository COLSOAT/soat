package com.mercadopago.resources.point;

import com.mercadopago.net.MPResource;
import lombok.Getter;

import java.util.List;

/** PointPaymentIntentList class. */
@Getter
public class PointPaymentIntentList extends MPResource {
  /** List of events. */
  private List<PointPaymentIntentListEvent> events;
}
