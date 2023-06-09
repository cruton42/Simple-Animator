package cs3500.animator.model;

import java.util.List;

/**
 * Interface representing the model for a simple animator program.
 */
public interface SimpleAnimatorModel {

  /**
   * Adds a Motion when given the shape, position to be moved to, start tick, and end tick if the
   * Motion is valid given the already existing motions. Assumes that the user does not give any
   * different shapes the same name.
   *
   * @param shape       shape to be moved
   * @param start       tick to start motion on
   * @param end         tick to end motion on
   * @param toPos       position to move to, or null if the position is not changing in the Motion
   * @param colorChange color to change to, or null if the color is not changing in the Motion
   * @throws IllegalArgumentException if the given shape and start and end ticks overlap with an
   *                                  existing motion, or if the shape is not a valid member of the
   *                                  Array of shapes
   */
  public void addMotion(ShapeDetails shape, int start, int end,
      Position2D startPos, OurColor startCol, Position2D toPos,
      OurDimensions toDim, OurColor colorChange);

  /**
   * Method to add a shape to the list of shapes being animated.
   * @param shape shape to be added
   */
  public void addShape(ShapeDetails shape);


  /**
   * Method that gets a list of all motions associated with the given a shape.
   * @param shape a ShapeDetails to get the motions of
   * @return an ArrayList of Motions associated with the given ShapeDetails
   */
  public List<Motion> getMotions(ShapeDetails shape);

  /**
   * Method that returns the list of shapes associated with the animation.
   * @return an ArrayList of ShapeDetails to be animated
   */
  public List<ShapeDetails> getShapes();

  /**
   * Sets the constraints to be used for the view, including the x and y in the corner, the width
   *            and the height of the screen.
   * @param x the x value of the top corner
   * @param y the y value of the top corner
   * @param width the width of the screen
   * @param height the height of the screen
   */
  public void setConstraints(int x, int y, int width, int height);

  /**
   * Returns the x value of the top corner to be used in the view.
   * @return the x value of the top corner, as set.
   */
  public int getX();

  /**
   * Returns the y value of the top corner to be used in the view.
   * @return the y value of the top corner, as set.
   */
  public int getY();

  /**
   * Returns the width to be used in the view.
   * @return the width, as set.
   */
  public int getWidth();

  /**
   * Returns the height to be used in the view.
   * @return the height, as set.
   */
  public int getHeight();

}
