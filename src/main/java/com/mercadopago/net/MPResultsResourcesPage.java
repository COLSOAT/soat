package com.mercadopago.net;

import com.mercadopago.resources.ResultsPaging;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * MPResultsResourcesPage class.
 *
 * @param <T> class type
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MPResultsResourcesPage<T> extends MPResource {
  private ResultsPaging paging;

  private List<T> results;
}
