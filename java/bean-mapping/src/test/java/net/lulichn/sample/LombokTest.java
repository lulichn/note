package net.lulichn.sample;

import net.lulichn.sample.common.Address;
import net.lulichn.sample.common.ExAddress;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LombokTest {
  @Test
  public void test() {
    var a = new Address()
        .setZip("1234").setAddress1("aaaa").setAddress2("bbbb");
    var b = new Address()
        .setZip("1234").setAddress1("aaaa").setAddress2("bbbbc");

    assertNotEquals(a, b);
  }

  @Test
  public void test2() {
    var a = (ExAddress)new ExAddress()
        .setZip("1234").setAddress1("aaaa").setAddress2("bbbb");
    a.setRoom("12");
    var b = (ExAddress)new ExAddress()
        .setZip("1234").setAddress1("aaaa").setAddress2("bbbb");
    b.setRoom("123");

    assertNotEquals(a, b);
  }
}
