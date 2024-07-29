package com.mercadopago.net;

import lombok.Getter;

import java.util.List;

/**
 * Search page that contains elements property.
 *
 * @param <T> type
 */
@Getter
public class MPElementsResourcesPage<T> extends MPResource {
  /** The total number of items that match search criteria. */
  private int total;

  /** Offset of the next page. */
  private int nextOffset;

  /** Items in this page. */
  private List<T> elements;
}
