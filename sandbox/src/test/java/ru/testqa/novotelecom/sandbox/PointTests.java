package ru.testqa.novotelecom.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void Distance() {
    Point p1 = new Point(3,4);
    Point p2 = new Point(5,4);
    //assert p1.distance(p1,p2) == 2.0;
    Assert.assertEquals(p1.distance(p2),2.0);
    Assert.assertEquals(p1.distance(p2),2);
    Assert.assertEquals(p1.distance(p2),25.0);
    Assert.assertEquals(p1.distance(p2),-2.0);



  }

}
