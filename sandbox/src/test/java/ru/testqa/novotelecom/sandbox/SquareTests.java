package ru.testqa.novotelecom.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

  @Test
  public void Area() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }
}
