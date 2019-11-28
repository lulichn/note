package net.lulichn.sample.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Source {
  private int id;
  private String name;
  private Sex sex;
  private Address address;
}
