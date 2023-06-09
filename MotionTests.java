
import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Motion class and its methods.
 */
public class MotionTests {

  @Test
  public void testFirstConstructor() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 5, nextPos, sd.getDimension(), nextCol);
    assertEquals("motion SD 1 99 9 32 15 66 1 22   5 2 100 32 15 19 2 117", m.getInfo());
  }

  @Test
  public void testSecondConstructor() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 5, nextPos);
    assertEquals("motion SD 1 99 9 32 15 66 1 22   5 2 100 32 15 66 1 22", m.getInfo());
  }

  @Test
  public void testThirdConstructor() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(2, 1);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(22, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 9, nextCol);
    assertEquals("motion SD 1 2 1 32 15 22 1 22   9 2 1 32 15 19 2 117", m.getInfo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStart() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, -1, 7, nextPos,  sd.getDimension(), nextCol);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    Position2D nextPos = new Position2D(2, 100);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = null;

    Motion m = new Motion(sd, 1, -8, nextPos,  null, nextCol);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEnd() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, -8, nextPos,  sd.getDimension(), nextCol);
  }

  @Test
  public void testStartSameAsEnd() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 4, 4, nextPos,  sd.getDimension(), nextCol);
    assertTrue(m.sameShape(sd));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGreaterThanEnd() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 7, 4, nextPos,  sd.getDimension(), nextCol);
  }

  @Test
  public void testSameShape() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);
    ShapeDetails sd2 = new ShapeDetails("SD", s, p, d, c);
    ShapeDetails sd3 = new ShapeDetails("SD3", OurShape.ELLIPSE, p, d, c);
    ShapeDetails sd4 = new ShapeDetails("SD4", s, nextPos, d, c);
    ShapeDetails sd5 = new ShapeDetails("SD5", s, nextPos, d, nextCol);

    Motion m = new Motion(sd, 1, 5, nextPos,  sd.getDimension(), nextCol);

    // checks various relations for the same shape
    assertTrue(m.sameShape(sd));
    assertTrue(m.sameShape(sd2));
    assertFalse(m.sameShape(sd3));
    assertFalse(m.sameShape(sd4));
    assertFalse(m.sameShape(sd5));
  }

  @Test
  public void testDoesOverlap() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(9, 91);
    Position2D nextPos = new Position2D(11, 10);
    OurDimensions d = new OurDimensions(111, 22);
    OurColor c = new OurColor(6, 122, 32);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);
    ShapeDetails sd2 = new ShapeDetails("SD", s, p, d, nextCol);
    ShapeDetails sd3 = new ShapeDetails("SD3", s, p, d, nextCol);

    Motion m = new Motion(sd, 5, 12, nextPos,  sd.getDimension(), nextCol);

    // checks various relations that do or do not overlap
    assertTrue(m.doesOverlap(6, 11));
    assertTrue(m.doesOverlap(4, 6));
    assertTrue(m.doesOverlap(3, 11));

    assertFalse(m.doesOverlap(3, 12));
    assertFalse(m.doesOverlap(1, 3));
    assertFalse(m.doesOverlap(3, 4));
    assertFalse(m.doesOverlap(13, 21));
  }

  @Test
  public void testGetInfo() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(2, 1);
    Position2D nextPos = new Position2D(1, 5);
    OurDimensions d = new OurDimensions(5, 10);
    OurColor c = new OurColor(6, 122, 232);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 15, nextPos,  sd.getDimension(), nextCol);
    Motion m2 = new Motion(sd, 2, 300, nextPos,  sd.getDimension(), nextCol);
    Motion m3 = new Motion(sd, 2, 155, nextPos);
    Motion m4 = new Motion(sd, 19, 20, nextCol);

    assertEquals("motion SD 1 2 1 5 10 6 122 232   15 1 5 5 10 19 2 117", m.getInfo());
    assertEquals("motion SD 2 2 1 5 10 6 122 232   300 1 5 5 10 19 2 117", m2.getInfo());
    assertEquals("motion SD 2 2 1 5 10 6 122 232   155 1 5 5 10 6 122 232", m3.getInfo());
    assertEquals("motion SD 19 2 1 5 10 6 122 232   20 2 1 5 10 19 2 117", m4.getInfo());
  }

  @Test
  public void testGetColor() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 5, nextPos,  sd.getDimension(), nextCol);
    assertEquals(c, m.getColor(true));
    assertEquals(nextCol, m.getColor(false));
  }

  @Test
  public void testGetPos() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 5, nextPos, sd.getDimension(), nextCol);
    assertEquals(p, m.getPosition(true));
    assertEquals(nextPos, m.getPosition(false));
  }

  @Test
  public void testGetTick() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);

    Motion m = new Motion(sd, 1, 5, nextPos,  sd.getDimension(), nextCol);

    assertEquals(1, m.getTick(true));
    assertEquals(5, m.getTick(false));
  }


}
