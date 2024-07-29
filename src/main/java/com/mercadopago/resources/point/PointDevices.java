package com.mercadopago.resources.point;

import com.mercadopago.net.MPResource;
import com.mercadopago.resources.ResultsPaging;
import lombok.Getter;

import java.util.List;

/** Point devices resource. */
@Getter
public class PointDevices extends MPResource {
  /** List of devices. */
  private List<PointDevice> devices;

  /** Paging information. */
  private ResultsPaging paging;
}
