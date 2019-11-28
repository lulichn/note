package net.lulichn.sample.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)

public class Address {
  private String zip;
  private String address1;
  private String address2;
}
