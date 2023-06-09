import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for ShapeDetails and its methods.
 */
public class ShapeDetailsTests {

  @Test
  public void testConstructor() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(25, 50);
    OurDimensions d = new OurDimensions(10, 5);
    OurColor c = new OurColor(100, 100, 100);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);

    assertEquals("25 50 10 5 100 100 100", sd.getInfo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName() {
    OurShape s = OurShape.ELLIPSE;
    Position2D p = new Position2D(2, 50);
    OurDimensions d = new OurDimensions(1, 5);
    OurColor c = new OurColor(667, 10, 120);

    ShapeDetails sd = new ShapeDetails(null, s, p, d, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShape() {
    OurShape s = null;
    Position2D p = new Position2D(2, 50);
    OurDimensions d = new OurDimensions(1, 5);
    OurColor c = new OurColor(667, 10, 120);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosition() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = null;
    OurDimensions d = new OurDimensions(10, 5);
    OurColor c = new OurColor(100, 100, 100);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensions() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(11, 11);
    OurDimensions d = null;
    OurColor c = new OurColor(100, 100, 100);

    ShapeDetails sd = new ShapeDetails("R", s, p, d, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColor() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(11, 11);
    OurDimensions d = new OurDimensions(1234, 22);
    OurColor c = null;

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);
  }

  @Test
  public void testGetInfo() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(50, 22);
    OurDimensions d = new OurDimensions(12, 1);
    OurColor c = new OurColor(255, 225, 252);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);

    ShapeDetails sd2 = new ShapeDetails("S", OurShape.ELLIPSE, new Position2D(50, 22),
        new OurDimensions(12, 1), new OurColor(255, 225, 252));
    ShapeDetails sd3 = new ShapeDetails("R", OurShape.RECTANGLE, new Position2D(2, 21),
        new OurDimensions(13, 2), new OurColor(215, 25, 92));

    // checks that the getInfo is correct
    assertEquals("50 22 12 1 255 225 252", sd.getInfo());
    assertEquals("2 21 13 2 215 25 92", sd3.getInfo());

    // checks for cases when the getInfo results should be the same or different
    assertEquals(sd2.getInfo(), sd.getInfo());
    assertFalse(sd3.getInfo().equals(sd.getInfo()));
  }

  @Test
  public void testGetShapeString() {
    OurShape s = OurShape.RECTANGLE;
    OurShape s2 = OurShape.ELLIPSE;
    Position2D p = new Position2D(50, 22);
    OurDimensions d = new OurDimensions(12, 1);
    OurColor c = new OurColor(255, 225, 252);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);
    ShapeDetails sd2 = new ShapeDetails("R", s2, p, d, c);

    assertEquals("RECTANGLE", sd.getShapeString());
    assertEquals("ELLIPSE", sd2.getShapeString());
  }

  @Test
  public void testGetName() {
    OurShape s = OurShape.RECTANGLE;
    OurShape s2 = OurShape.RECTANGLE;
    Position2D p = new Position2D(50, 22);
    OurDimensions d = new OurDimensions(12, 1);
    OurColor c = new OurColor(255, 225, 252);

    ShapeDetails sd = new ShapeDetails("S", s, p, d, c);
    ShapeDetails sd2 = new ShapeDetails("R", s2, p, d, c);
    ShapeDetails sd3 = new ShapeDetails("S", s, p, d, c);

    assertEquals("S", sd.getName());
    assertEquals("R", sd2.getName());
    assertEquals(sd3.getName(), sd.getName());
  }

  @Test
  public void testPositionChange() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(50, 22);
    Position2D nextPos = new Position2D(100, 8);
    OurDimensions d = new OurDimensions(12, 1);
    OurColor c = new OurColor(255, 225, 252);

    ShapeDetails sd = new ShapeDetails("R", s, p, d, c);
    ShapeDetails result = new ShapeDetails("R", s, nextPos, d, c);

    // checks for equality
    assertEquals(result, sd.updateShape(nextPos, null, null));
    assertEquals(result.getShapeString(), sd.getShapeString());
    assertEquals(sd, result.updateShape(p, null, null));
  }

  @Test
  public void testColorChange() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    OurDimensions d = new OurDimensions(12, 1);
    OurColor c = new OurColor(8, 7, 252);
    OurColor nextCol = new OurColor(189, 200, 7);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);
    ShapeDetails result = new ShapeDetails("SD", s, p, d, nextCol);

    // checks for equality
    assertEquals(result, sd.updateShape(null, null, nextCol));
    assertEquals(result.getShapeString(), sd.getShapeString());
    assertEquals(sd, result.updateShape(null, null, c));
  }

  @Test
  public void testPositionColorChange() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    Position2D nextPos = new Position2D(2, 100);
    OurDimensions d = new OurDimensions(32, 15);
    OurColor c = new OurColor(66, 1, 22);
    OurColor nextCol = new OurColor(19, 2, 117);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);
    ShapeDetails result = new ShapeDetails("SD", s, nextPos, d, nextCol);

    // checks for equality
    assertEquals(result, sd.updateShape(nextPos, null, nextCol));
    assertEquals(result.getShapeString(), sd.getShapeString());
    assertEquals(sd, result.updateShape(p, null, c));
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
    ShapeDetails sd2 = new ShapeDetails("SD2", s, nextPos, d, nextCol);

    assertEquals(p, sd.getPos());
    assertEquals(nextPos, sd2.getPos());
  }

  @Test
  public void testGetColor() {
    OurShape s = OurShape.RECTANGLE;
    Position2D p = new Position2D(99, 9);
    OurDimensions d = new OurDimensions(12, 1);
    OurColor c = new OurColor(8, 7, 252);
    OurColor nextCol = new OurColor(189, 200, 7);

    ShapeDetails sd = new ShapeDetails("SD", s, p, d, c);
    ShapeDetails sd2 = new ShapeDetails("SD2", s, p, d, nextCol);

    // checks for equality
    assertEquals(c, sd.getColor());
    assertEquals(nextCol, sd2.getColor());
  }

}
