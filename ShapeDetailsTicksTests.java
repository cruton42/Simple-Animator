import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.ShapeDetailsTick;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ShapeDetailsTick.
 */
public class ShapeDetailsTicksTests {

  @Test
  public void testFirstConstructor() {
    ShapeDetailsTick test = new ShapeDetailsTick("SD", OurShape.RECTANGLE, new Position2D(10, 20),
        new OurDimensions(400, 30), new OurColor(125, 200, 25), 50);

    assertEquals("10 20 400 30 125 200 25", test.getInfo());
    assertEquals(50, test.getTick());
  }

  @Test
  public void testSecondConstructor() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(25, 50);
    OurDimensions d = new OurDimensions(10, 5);
    OurColor c = new OurColor(100, 100, 100);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);

    ShapeDetailsTick sdt = new ShapeDetailsTick(sd, 36);

    assertEquals("25 50 10 5 100 100 100", sdt.getInfo());
    assertEquals(36, sdt.getTick());
  }

  @Test
  public void testGetTick() {
    ShapeDetailsTick test = new ShapeDetailsTick("SD", OurShape.RECTANGLE, new Position2D(10, 20),
        new OurDimensions(400, 30), new OurColor(125, 200, 25), 25);

    assertEquals(25, test.getTick());
  }
}