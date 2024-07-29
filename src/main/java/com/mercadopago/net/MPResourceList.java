package com.mercadopago.net;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * List of resources returned by an API.
 *
 * @param <T> Type of resource being returned
 */
@Getter
@Setter
public class MPResourceList<T> extends MPResource {
  /** List of results. */
  private List<T> results;
}
