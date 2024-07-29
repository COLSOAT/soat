package com.mercadopago.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/** MPResponse class. */
@Getter
@AllArgsConstructor
public class MPResponse {

  private final Integer statusCode;

  private final Map<String, List<String>> headers;

  private final String content;
}
