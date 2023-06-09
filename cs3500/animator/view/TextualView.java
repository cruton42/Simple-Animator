package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a console output of the textual description of the animation. Similar to the
 *            initial getDescription method of the model used in earlier assignment.
 */
public class TextualView implements IView {
  private List<ShapeDetails> shapes;
  private List<Motion> motions;
  private Position2D minD;
  private int width;
  private int height;

  /**
   * Constructs a textual view with the given motions to be displayed as text.
   */
  public TextualView() {
    this.shapes = new ArrayList<ShapeDetails>();
    this.motions = new ArrayList<Motion>();
    this.minD = new Position2D(0,0);
    this.width = 0;
    this.height = 0;
  }

  @Override
  public void refresh() {
    //Console view doesn't need this method
  }

  @Override
  public void addShapes(List<ShapeDetails> shapes) {
    this.shapes = shapes;
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
  public void setSpeed(int speed) throws UnsupportedOperationException {
    // not required because we do not need to store speed in this class
    throw new UnsupportedOperationException("Unsupported operation for TextualView");
  }

  @Override
  public String getTextualRepresentation() {
    int count = -1;
    StringBuilder result = new StringBuilder();

    result.append("canvas " + this.minD.getInfo() + " " + this.width + " " + this.height + "\n");

    for (int i = 0; i < shapes.size(); i++) {
      result.append("shape " + shapes.get(i).getName() + " "
          + shapes.get(i).getShapeString().toLowerCase() + "\n");

      count = this.getCount(i);

      for (int m = 0; m < motions.size(); m++) {
        if (motions.get(m).sameShape(shapes.get(i))) {
          result.append(motions.get(m).getInfo());
          count--;

          if (count != 0) {
            result.append("\n");
          }
        }
      }

      if (i != shapes.size() - 1) {
        result.append("\n");
      }
    }

    return result.toString();
  }
}
