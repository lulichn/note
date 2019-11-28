package net.lulichn.sample.common;

import lombok.Data;

@Data
public class Destination {
  private int id;
  private String name;
  private Sex sex;
  private Address address;
}
