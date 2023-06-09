import static org.junit.Assert.assertEquals;

import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextualView;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Test class for TextualView.
 */
public class TextualViewTests {

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
  public void testEmptyTextualView() {
    IView tv = new TextualView();
    assertEquals("canvas 0 0 0 0\n", tv.getTextualRepresentation());
  }

  @Test
  public void testAddShapes() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);

    IView tv = new TextualView();
    tv.addShapes(sdListBigger);
    assertEquals("canvas 0 0 0 0\n"
        + "shape SD rectangle\n"
        + "\n"
        + "shape SD2 ellipse\n"
        + "\n"
        + "shape SD3 ellipse\n", tv.getTextualRepresentation());
  }

  @Test
  public void testSetMotions() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);

    mList.add(m);
    mList.add(m2);
    mList.add(m3);

    IView tv = new TextualView();
    tv.addShapes(sdListBigger);
    tv.setMotions(mList);
    assertEquals("canvas 0 0 0 0\n"
        + "shape SD rectangle\n"
        + "motion SD 1 99 9 12 1 8 7 252   3 22 69 12 1 2 2 2\n"
        + "motion SD 4 22 69 12 1 2 2 2   6 11 61 12 1 2 2 2\n"
        + "shape SD2 ellipse\n"
        + "motion SD2 1 22 1 3 11 12 3 25   7 2 9 3 11 12 3 25\n"
        + "shape SD3 ellipse\n", tv.getTextualRepresentation());
  }

  @Test
  public void testSetCanvas() {
    initConditions();

    IView tv = new TextualView();
    tv.setCanvas(21, 12, 333, 441);
    assertEquals("canvas 21 12 333 441\n", tv.getTextualRepresentation());
  }

  @Test
  public void testGetTextual() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);

    mList.add(m);
    mList.add(m2);
    mList.add(m3);

    IView tv = new TextualView();
    tv.addShapes(sdListBigger);
    tv.setMotions(mList);
    tv.setCanvas(-5, 1000, 25, 3000);
    assertEquals("canvas -5 1000 25 3000\n"
        + "shape SD rectangle\n"
        + "motion SD 1 99 9 12 1 8 7 252   3 22 69 12 1 2 2 2\n"
        + "motion SD 4 22 69 12 1 2 2 2   6 11 61 12 1 2 2 2\n"
        + "shape SD2 ellipse\n"
        + "motion SD2 1 22 1 3 11 12 3 25   7 2 9 3 11 12 3 25\n"
        + "shape SD3 ellipse\n", tv.getTextualRepresentation());
  }
}
