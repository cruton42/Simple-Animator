package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.ShapeDetails;
import java.util.List;

/**
 * Interface representing a view for the SimpleAnimatorModel. Allows for multiple different types
 *          of views that display detials or show images that represent
 *          a series of motions of shapes.
 */
public interface IView {

  /**
   * Refreshes the image displayed by the view.
   */
  void refresh();

  /**
   * Adds shapes to the view as required.
   * @param shapes List of shapes to be added to the view
   */
  void addShapes(List<ShapeDetails> shapes);

  /**
   * Sets the size of the screen given the x and y value of the top corner, the width and height.
   *
   * @param x x value of the top corner
   * @param y y value of the top corner
   * @param width desired width of the screen
   * @param height desired height of the screen
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Adds the motions to be represented by the view.
   * @param motions the list of motions as a List of motions
   */
  void setMotions(List<Motion> motions);

  /**
   * Returns a String representing the textual representation of the view.
   * @return a String representing the textual representation of the view
   */
  String getTextualRepresentation();

  /**
   * Sets the speed of the animations in ticks per second.
   * @param speed ticks per second value for the speed of the animation
   */
  void setSpeed(int speed);

}
