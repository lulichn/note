package net.lulichn.sample.orika;

import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.lulichn.sample.common.Address;
import net.lulichn.sample.common.Destination;
import net.lulichn.sample.common.Sex;
import net.lulichn.sample.common.Source;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrikaSample {
  @Test
  public void sample() {
    var mapperFactory = new DefaultMapperFactory.Builder().build();
    var mapper = mapperFactory.getMapperFacade();

    var src = new Source()
        .setId(10)
        .setName("foo bar")
        .setSex(Sex.MALE)
        .setAddress(new Address()
            .setZip("123456")
            .setAddress1("1-2-3")
            .setAddress2("main-st"));

    var dest = mapper.map(src, Destination.class);
    assertEquals(10,  dest.getId());
  }
}
