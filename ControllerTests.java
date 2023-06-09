import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.SimpleAnimationController;
import cs3500.animator.controller.SimpleAnimatorControllerImpl;
import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.SimpleAnimatorModel;
import cs3500.animator.model.SimpleAnimatorModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Tests for the SimpleAnimatorControllerImpl class.
 */
public class ControllerTests {
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
  Motion m4 = null;
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
    m4 = new Motion(sd3, 1, 7, new Position2D(2, 9), null, null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalViewTypeString() {
    SimpleAnimationController sac = new SimpleAnimatorControllerImpl(3, new StringBuilder());
    sac.runAnimation(new SimpleAnimatorModelImpl(sdList), "Banana", "car.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    SimpleAnimationController sac = new SimpleAnimatorControllerImpl(3, new StringBuilder());
    sac.runAnimation(null, "textual", "system.out");
  }

  @Test
  public void testTextualMaking() {
    initConditions();
    Appendable out = new StringBuilder();

    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);

    mList.add(m);
    mList.add(m2);
    mList.add(m3);

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdListBigger, mList);
    sam.setConstraints(2,3,44,555);
    SimpleAnimationController sac = new SimpleAnimatorControllerImpl(3, out);
    sac.runAnimation(sam, "text", "tst");
    assertEquals("canvas 2 3 44 555\n"
        + "shape SD rectangle\n"
        + "motion SD 1 99 9 12 1 8 7 252   3 22 69 12 1 2 2 2\n"
        + "motion SD 4 22 69 12 1 2 2 2   6 11 61 12 1 2 2 2\n"
        + "shape SD2 ellipse\n"
        + "motion SD2 1 22 1 3 11 12 3 25   7 2 9 3 11 12 3 25\n"
        + "shape SD3 ellipse\n", out.toString());

    // checks equality with created TextualView
    IView tv = new TextualView();
    tv.addShapes(sdListBigger);
    tv.setMotions(mList);
    tv.setCanvas(2, 3, 44, 555);
    assertEquals(out.toString(), tv.getTextualRepresentation());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetSpeed() {
    IView tv = new TextualView();
    tv.setSpeed(2000000);
  }

  @Test
  public void testSVGMaking() {
    initConditions();
    Appendable out = new StringBuilder();

    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);

    mList.add(m);
    mList.add(m2);
    mList.add(m3);
    mList.add(m4);

    SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(sdListBigger, mList);
    sam.setConstraints(2,3,44,555);
    SimpleAnimationController sac = new SimpleAnimatorControllerImpl(3, out);
    sac.runAnimation(sam, "svg", "tst");
    assertEquals("<svg width=\"44\" height=\"555\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"SD\" x=\"97\" y=\"6\" width=\"12\" height=\"1\" fill=\"rgb(8,7,252)\" "
        + "visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"666ms\" attributeName=\"x\" "
        + "from=\"97\" to=\"20\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"666ms\" attributeName=\"y\" "
        + "from=\"6\" to=\"66\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"666ms\" attributeName=\"fill\""
        + " from=\"rgb(8,7,252)\" to=\"rgb(2,2,2)\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"1333ms\" dur=\"666ms\" attributeName=\"x\""
        + " from=\"20\" to=\"9\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"1333ms\" dur=\"666ms\" attributeName=\"y\""
        + " from=\"66\" to=\"58\" fill=\"freeze\" />\n"
        + "\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"SD2\" cx=\"20\" cy=\"-2\" rx=\"3\" ry=\"11\" fill=\"rgb(12,3,25)\" "
        + "visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"2000ms\" attributeName=\"cx\""
        + " from=\"20\" to=\"0\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"2000ms\" attributeName=\"cy\""
        + " from=\"-2\" to=\"6\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "<ellipse id=\"SD3\" cx=\"31\" cy=\"9\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"2000ms\" attributeName=\"cx\""
        + " from=\"31\" to=\"0\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"333ms\" dur=\"2000ms\" attributeName=\"cy\""
        + " from=\"9\" to=\"6\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", out.toString());

    // checks equality with created TextualView
    IView svg = new SVGView();
    svg.addShapes(sdListBigger);
    svg.setMotions(mList);
    svg.setSpeed(2000000);
    svg.setCanvas(2, 3, 44, 555);
    svg.setSpeed(3);
    assertEquals(out.toString(), svg.getTextualRepresentation());
  }


}
