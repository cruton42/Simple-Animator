package cs3500.animator.view;

import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


/**
 * Represents the panel of the VisualView. Used to hold and show the actual shapes and their
 *            motions on the screen. Shows the image of the shapes at each tick.
 */
public class VisualPanel extends JPanel {

  private List<ShapeDetails> shapes;
  //the rectangle within which the animation occurs
  private Position2D minD;
  private int width;
  private int height;

  /**
   * Constructs a VisualPanel. Sets the background color and allows for the display to be seen.
   */
  public VisualPanel() {
    super();
    this.setBackground(Color.WHITE);

    this.minD = new Position2D(0, 0);
    this.width = 0;
    this.height = 0;

    this.shapes = new ArrayList<ShapeDetails>();

    this.setMinimumSize(new Dimension(1000,1000));

    //this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    // does not run in typical use, only for tests primarily
    if (width <= 0 || height <= 0) {
      return new Dimension(1000,1000);
    }

    return new Dimension(width,height);
  }

  /**
   * Sets the size of the canvas given the x and y value of the top corner, the width and height.
   *
   * @param x x value of the top corner
   * @param y y value of the top corner
   * @param width desired width of the screen
   * @param height desired height of the screen
   */
  public void setCanvas(int x, int y, int height, int width) {
    this.minD = new Position2D(x,y);
    this.height = height;
    this.width = width;
  }

  /**
   * Sets the given list of shapes to this shapes field.
   * @param shapes List of shapes to be set to this field
   */
  public void setShapes(List<ShapeDetails> shapes) {
    if (shapes == null) {
      throw new IllegalArgumentException("Shapes cannot be null.");
    }
    else {
      this.shapes = shapes;
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < this.shapes.size(); i++) {
      ShapeDetails s = this.shapes.get(i);

      Color tempCol = new Color(s.getColor().getR(), s.getColor().getG(), s.getColor().getB());
      g2d.setColor(tempCol);

      if (s.getShapeString().equals("ELLIPSE")) {
        g2d.fillOval(s.getPos().getX() - this.minD.getX(), s.getPos().getY() - this.minD.getY(),
            s.getDimension().getWidth(), s.getDimension().getHeight());
      }
      else if (s.getShapeString().equals("RECTANGLE")) {
        g2d.fillRect(s.getPos().getX() - this.minD.getX(), s.getPos().getY() - this.minD.getY(),
            s.getDimension().getWidth(), s.getDimension().getHeight());
      }
    }
  }
}
