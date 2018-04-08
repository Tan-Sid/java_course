package ru.testqa.novotelecom.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void Distance() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(5, 4);
    Assert.assertEquals(p1.distance(p2), 2.0);
  }

  @Test
  public void Distance1() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test(enabled = false)
  public void Distance2() {
    Point p1 = new Point(-3, -4);
    Point p2 = new Point(-5, -4);
    Assert.assertEquals(p1.distance(p2)>0, "Расстояние не может быть < 0");
  }

}
