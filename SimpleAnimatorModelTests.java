import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.SimpleAnimatorModel;
import cs3500.animator.model.SimpleAnimatorModelImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for SimpleAnimatorModel and its methods.
 */
public class SimpleAnimatorModelTests {

  OurShape s = null;
  Position2D p = null;
  OurDimensions d = null;
  OurColor c = null;
  ShapeDetails sd = null;
  ShapeDetails sd2 = null;
  ShapeDetails sd3 = null;
  Motion m = null;
  Motion m2 = null;
  Motion m3 = null;
  List<ShapeDetails> sdList = new ArrayList<ShapeDetails>();
  List<ShapeDetails> sdListBigger = new ArrayList<ShapeDetails>();
  List<Motion> mList = new ArrayList<Motion>();

  private void initConditions() {
    s = OurShape.RECTANGLE;
    p = new Position2D(99, 9);
    d = new OurDimensions(12, 1);
    c = new OurColor(8, 7, 252);
    sd = new ShapeDetails("SD", s, p, d, c);
    sd2 = new ShapeDetails("SD2", OurShape.ELLIPSE, new Position2D(22, 1),
        new OurDimensions(3, 11), new OurColor(12, 3, 25));
    sd3 = new ShapeDetails("SD3", OurShape.ELLIPSE, new Position2D(33, 12),
        new OurDimensions(5, 5), new OurColor(0, 0, 255));

    m = new Motion(new ShapeDetails("SD", s, p, d, c), 1, 3, new Position2D(22, 69),
        null,
        new OurColor(2, 2, 2));
    m2 = new Motion(new ShapeDetails("SD", s, new Position2D(22, 69),
        d, new OurColor(2, 2, 2)), 4, 6, new Position2D(11, 61), null, null);
    m3 = new Motion(sd2, 1, 7, new Position2D(2, 9), null, null);
  }

  @Test
  public void testFirstConstructor() {
    initConditions();
    sdList.add(sd);

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList);
    assertEquals(sdList, sam.getShapes());
  }

  @Test
  public void testSecondConstructor() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);
    mList.add(m);
    mList.add(m2);
    mList.add(m3);

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdListBigger, mList);
    assertEquals(sdListBigger, sam.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlap() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);
    mList.add(m);
    mList.add(m2);
    mList.add(m3);
    mList.add(new Motion(new ShapeDetails("SD2", OurShape.ELLIPSE, new Position2D(2, 9),
        new OurDimensions(3, 11), new OurColor(12, 3, 25)),
        3, 9, new Position2D(6, 9), null, new OurColor(12, 3, 25)));

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdListBigger, mList);
    assertEquals(sdListBigger, sam.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorTeleport() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);
    mList.add(m);
    mList.add(m2);
    mList.add(m3);
    mList.add(new Motion(new ShapeDetails("SD", s, new Position2D(22, 69),
        d, new OurColor(3, 2, 2)),
        100, 210, new Position2D(6, 9), d, new OurColor(3, 2, 2)));

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdListBigger, mList);
    assertEquals(sdListBigger, sam.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPositionTeleport() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);
    mList.add(m);
    mList.add(m2);
    mList.add(m3);
    mList.add(new Motion(new ShapeDetails("SD", s, new Position2D(55, 69),
        d, new OurColor(2, 2, 2)),
        100, 210, new Position2D(6, 9), d, null));

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdListBigger, mList);
    assertEquals(sdListBigger, sam.getShapes());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullArrayConstructor() {
    initConditions();

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(null);
    assertEquals(null, sam.getShapes());
  }


  @Test
  public void testAddShape() {
    initConditions();

    sdList.add(sd);
    mList.add(new Motion(sdList.get(0), 44, 46,
        new Position2D(9, 6), sd.getDimension(), new OurColor(69, 66, 44)));

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList, mList);
    sam.addShape(sd2);
    sdList.add(sd2);
    assertEquals(sdList, sam.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNull() {
    initConditions();

    sdList.add(sd);

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList, mList);
    sam.addShape(null);
    assertEquals(null, sam.getShapes());
  }

  @Test
  public void testGetMotions() {
    initConditions();

    sdList.add(sd);
    mList.add(new Motion(sdList.get(0), 44, 46,
        new Position2D(9, 6), sd.getDimension(), new OurColor(69, 66, 44)));

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList, mList);

    assertEquals(mList, sam.getMotions(sd));

  }

  @Test
  public void testGetShape() {
    initConditions();

    sdList.add(sd);
    mList.add(new Motion(sdList.get(0), 44, 46,
        new Position2D(9, 6), sd.getDimension(), new OurColor(69, 66, 44)));

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList, mList);
    sam.addShape(sd2);
    sdList.add(sd2);
    assertEquals(sdList, sam.getShapes());
  }

  @Test
  public void testEmptyLists() {
    initConditions();

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList, mList);

    assertEquals(new ArrayList<ShapeDetails>(), sam.getShapes());
  }

  @Test
  public void setConstraints() {
    initConditions();

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdList, mList);
    sam.setConstraints(55, 22, 3000, 34000);
    assertEquals(55, sam.getX());
    assertEquals(22, sam.getY());
    assertEquals(3000, sam.getWidth());
    assertEquals(34000, sam.getHeight());
  }


}
