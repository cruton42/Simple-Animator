import static org.junit.Assert.assertEquals;

import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Tests for the SVGView class.
 */
public class SVGViewTests {

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


  @Test
  public void testEmptySVG() {
    IView svg = new SVGView();

    assertEquals("<svg width=\"0\" height=\"0\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "</svg>", svg.getTextualRepresentation());
  }

  @Test
  public void testAddShapesSetMotions() {
    initConditions();
    sdListBigger.add(sd);
    sdListBigger.add(sd2);
    sdListBigger.add(sd3);

    mList.add(m);
    mList.add(m2);
    mList.add(m3);
    mList.add(m4);

    IView svg = new SVGView();
    svg.addShapes(sdListBigger);
    svg.setMotions(mList);
    svg.setSpeed(2000000);
    
    assertEquals("<svg width=\"0\" height=\"0\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"SD\" x=\"99\" y=\"9\" width=\"12\" height=\"1\" fill=\"rgb(8,7,252)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"x\""
        + " from=\"99\" to=\"22\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"y\" "
        + "from=\"9\" to=\"69\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"fill\""
        + " from=\"rgb(8,7,252)\" to=\"rgb(2,2,2)\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"x\""
        + " from=\"22\" to=\"11\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"y\""
        + " from=\"69\" to=\"61\" fill=\"freeze\" />\n"
        + "\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"SD2\" cx=\"22\" cy=\"1\" rx=\"3\" ry=\"11\" fill=\"rgb(12,3,25)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cx\""
        + " from=\"22\" to=\"2\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cy\""
        + " from=\"1\" to=\"9\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "<ellipse id=\"SD3\" cx=\"33\" cy=\"12\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cx\""
        + " from=\"33\" to=\"2\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cy\""
        + " from=\"12\" to=\"9\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", svg.getTextualRepresentation());
  }

  @Test
  public void testSetCanvas() {
    initConditions();

    IView svg = new SVGView();
    svg.setCanvas(21, 12, 333, 441);
    assertEquals("<svg width=\"333\" height=\"441\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "</svg>", svg.getTextualRepresentation());
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
    mList.add(m4);

    IView svg = new SVGView();
    svg.addShapes(sdListBigger);
    svg.setMotions(mList);
    svg.setSpeed(2000000);
    svg.setCanvas(-5, 1000, 25, 3000);
    assertEquals("<svg width=\"25\" height=\"3000\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"SD\" x=\"104\" y=\"-991\" width=\"12\" height=\"1\" fill=\"rgb(8,7,252)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"x\""
        + " from=\"104\" to=\"27\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"y\""
        + " from=\"-991\" to=\"-931\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"fill\""
        + " from=\"rgb(8,7,252)\" to=\"rgb(2,2,2)\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"x\""
        + " from=\"27\" to=\"16\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"y\""
        + " from=\"-931\" to=\"-939\" fill=\"freeze\" />\n"
        + "\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"SD2\" cx=\"27\" cy=\"-999\" rx=\"3\" ry=\"11\" fill=\"rgb(12,3,25)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cx\""
        + " from=\"27\" to=\"7\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cy\""
        + " from=\"-999\" to=\"-991\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "<ellipse id=\"SD3\" cx=\"38\" cy=\"-988\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cx\""
        + " from=\"38\" to=\"7\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"cy\""
        + " from=\"-988\" to=\"-991\" fill=\"freeze\" />\n"
        + "\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", svg.getTextualRepresentation());
  }
}
