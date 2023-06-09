package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the view in the style of a SVG animation.
 *      Making visible creates the text in SVG style to represent the model and its motions.
 *      Contains the shapes, motions, and details necessary for the canvas of the model.
 */
public class SVGView implements IView {
  private List<ShapeDetails> shapes;
  private List<Motion> motions;
  private Position2D minD;
  private int width;
  private int height;
  private int speed;

  /**
   * Creates an SVG model with initialized variables. Intended to overwrite each variable.
   */
  public SVGView() {
    this.shapes = new ArrayList<ShapeDetails>();
    this.motions = new ArrayList<Motion>();
    this.minD = new Position2D(0,0);
    this.width = 0;
    this.height = 0;
    this.speed = 0;
  }

  @Override
  public void refresh() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported operation for SVGView");
  }

  @Override
  public void addShapes(List<ShapeDetails> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.minD = new Position2D(x,y);
    this.width = width;
    this.height = height;
  }

  @Override
  public void setMotions(List<Motion> motions) {
    this.motions = motions;
  }

  @Override
  public String getTextualRepresentation() {
    int count = -1;
    StringBuilder result = new StringBuilder();

    result.append("<svg width=\"" + this.width + "\" height=\"" + this.height
        + "\" version=\"1.1\"\n" + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    for (int i = 0; i < shapes.size(); i++) {
      ShapeDetails temp = shapes.get(i);
      Motion firstMotion = null;
      for (int r = 0; r < motions.size(); r++) {
        if (motions.get(r).sameShape(temp)) {
          firstMotion = motions.get(r);
          result.append(getShapeDeclaration(firstMotion));
          r = motions.size();
        }
      }
      count = this.getCount(i);

      for (int m = 0; m < motions.size(); m++) {
        if (motions.get(m).sameShape(temp)) {
          result.append(getAnimationXML(temp, motions.get(m)));
          count--;
        }
      }

      result.append("\n");

      if (firstMotion == null) {
        throw new IllegalArgumentException("Shape inputted with no motions");
      }
      else if (firstMotion.getShape(true).getShapeString().toLowerCase().equals("rectangle")) {
        result.append("</rect>\n\n");
      }
      else if (firstMotion.getShape(true).getShapeString().toLowerCase().equals("ellipse")) {
        result.append("</ellipse>\n\n");
      }
    }

    result.append("</svg>");

    return result.toString();
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Returns a SVG String to represent the given motion as an animation.
   * @param temp the ShapeDetails that the Motion pertains to
   * @param motion the motion we are trying to represent
   * @return the animation as a String in the SVG style
   */
  private String getAnimationXML(ShapeDetails temp, Motion motion) {
    StringBuilder result = new StringBuilder();
    List<String> inputs = new ArrayList<String>();

    if (motion.getPosition(true).getX() != motion.getPosition(false).getX()) {

      if (temp.getShapeString().toLowerCase().equals("rectangle")) {
        inputs.add("x");
      } else if (temp.getShapeString().toLowerCase().equals("ellipse")) {
        inputs.add("cx");
      }

      inputs.add((motion.getPosition(true).getX() - this.minD.getX()) + "");
      inputs.add((motion.getPosition(false).getX() - this.minD.getX()) + "");
    }

    if (motion.getPosition(true).getY() != motion.getPosition(false).getY()) {

      if (temp.getShapeString().toLowerCase().equals("rectangle")) {
        inputs.add("y");
      } else if (temp.getShapeString().toLowerCase().equals("ellipse")) {
        inputs.add("cy");
      }

      inputs.add((motion.getPosition(true).getY() - this.minD.getY()) + "");
      inputs.add((motion.getPosition(false).getY() - this.minD.getY()) + "");
    }

    if (motion.getShape(true).getDimension().getWidth()
        != motion.getShape(false).getDimension().getWidth()) {

      if (temp.getShapeString().equalsIgnoreCase("rectangle")) {
        inputs.add("width");
      } else if (temp.getShapeString().equalsIgnoreCase("ellipse")) {
        inputs.add("rx");
      }

      inputs.add(motion.getShape(true).getDimension().getWidth() + "");
      inputs.add(motion.getShape(false).getDimension().getWidth() + "");
    }

    if (motion.getShape(true).getDimension().getHeight()
        != motion.getShape(false).getDimension().getHeight()) {

      if (temp.getShapeString().equalsIgnoreCase("rectangle")) {
        inputs.add("height");
      } else if (temp.getShapeString().equalsIgnoreCase("ellipse")) {
        inputs.add("ry");
      }

      inputs.add(motion.getShape(true).getDimension().getHeight() + "");
      inputs.add(motion.getShape(false).getDimension().getHeight() + "");
    }

    if (!motion.getColor(true).equals(motion.getColor(false))) {
      inputs.add("fill");
      inputs.add("rgb(" + motion.getColor(true).getR() +
          "," + motion.getColor(true).getG() + "," + motion.getColor(true).getB() + ")");
      inputs.add("rgb(" + motion.getColor(false).getR() +
          "," + motion.getColor(false).getG() + "," + motion.getColor(false).getB() + ")");
    }

    while (inputs.size() > 0) {
      result.append("\t<animate attributeType=\"xml\" begin=\""
          + (int)(( 1.0 / speed) * (motion.getTick(true) * 1000.)) + "ms\" dur=\""
          + (int)(( 1.0 / speed)
          * ((motion.getTick(false) * 1000.) - (motion.getTick(true) * 1000.)))
          + "ms\" attributeName=\"");
      result.append(inputs.get(0) + "\" from=\"" + inputs.get(1)
          + "\" to=\"" + inputs.get(2) + "\" fill=\"freeze\" />\n");
      inputs.remove(0);
      inputs.remove(0);
      inputs.remove(0);
    }

    return result.toString();
  }

  /**
   * Returns the SVG String to represent the given ShapeDetails as expected.
   * @param firstMotion the first motion using the ShapeDetails we wish to represent
   * @return the declaration of the shape in SVG style
   */
  private String getShapeDeclaration( Motion firstMotion) {
    StringBuilder result = new StringBuilder();
    result.append("<");

    if (firstMotion.getShape(true).getShapeString().equalsIgnoreCase("rectangle")) {
      result.append("rect ");
      result.append("id=" + "\"" + firstMotion.getShape(true).getName() + "\" x=\""
          + (firstMotion.getShape(true).getPos().getX() - this.minD.getX()) + "\" y=\""
          + (firstMotion.getShape(true).getPos().getY() - this.minD.getY()) + "\" width=\""
          + firstMotion.getShape(true).getDimension().getWidth()
          + "\" height=\"" + firstMotion.getShape(true).getDimension().getHeight()
          + "\" fill=\"rgb("
          + firstMotion.getShape(true).getColor().getR()  + ","
          + firstMotion.getShape(true).getColor().getG()
          + "," + firstMotion.getShape(true).getColor().getB()
          + ")\" visibility=\"visible\" >");
    }
    else if (firstMotion.getShape(true).getShapeString().equalsIgnoreCase("ellipse")) {
      result.append("ellipse ");
      result.append("id=" + "\"" + firstMotion.getShape(true).getName() + "\" cx=\""
          + (firstMotion.getShape(true).getPos().getX() - this.minD.getX()) + "\" cy=\""
          + (firstMotion.getShape(true).getPos().getY() - this.minD.getY())
          + "\" rx=\"" + firstMotion.getShape(true).getDimension().getWidth()
          + "\" ry=\"" + firstMotion.getShape(true).getDimension().getHeight()
          + "\" fill=\"rgb("
          + firstMotion.getShape(true).getColor().getR() + ","
          + firstMotion.getShape(true).getColor().getG()
          + "," + firstMotion.getShape(true).getColor().getB()
          + ")\" visibility=\"visible\" >");
    }
    result.append("\n");

    return result.toString();
  }

  /**
   * Returns the number of motions involving the shape with the given index i in this.shapes.
   *
   * @param i int representing the index of the shape in question.
   * @return number of motions involving the shape with the given index i.
   */
  private int getCount(int i) {
    int count = 0;
    for (int m = 0; m < motions.size(); m++) {
      if (motions.get(m).sameShape(shapes.get(i))) {
        count++;
      }
    }
    return count;
  }

}
