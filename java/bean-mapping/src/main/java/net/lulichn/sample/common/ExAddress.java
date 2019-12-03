package net.lulichn.sample.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@EqualsAndHashCode(callSuper=true)
public class ExAddress extends Address{
  private String room;
}
