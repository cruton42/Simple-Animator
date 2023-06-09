package cs3500.animator.view;

import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.ShapeDetailsTick;
import java.util.List;

/**
 * Interface representing a view that involves editing the animations. Allows for more
 *                   functionality, including operations to adjust the animation while it runs.
 */
public interface EditInterface extends IView {

  /**
   * Returns the speed of the animations.
   * @return speed of the animations
   */
  int getSpeed();

  /**
   * Returns a boolean indicating if the animations have been restarted.
   * @return true if isRestarted is true, false otherwise
   */
  boolean isRestarted();

  /**
   * Sets the restart boolean field back to false after the ticks are restarted.
   */
  void restartRestart();

  /**
   * Returns a boolean indicating if the animations have been paused.
   * @return true if isPaused is true, false otherwise
   */
  boolean isPaused();

  /**
   * Returns a boolean indicating if the animations are allowed to loop.
   * @return true if the animations can loop, false otherwise
   */
  boolean isLooping();

  /**
   * Sets up the information regarding the keyframes based on the shapes available.
   * @param shapeNames the list of shapes to be used for their names
   */
  void setKeyFrames(List<ShapeDetails> shapeNames);

  /**
   * Sets all the booleans related to inputting equal to 0 to allow for possibility of a new input.
   */
  void resetInputBooleans();

  /**
   * Returns the input frame to be used depending on the operation being run on the frame.
   * @return the inputted frame
   */
  ShapeDetailsTick getInputFrame();

  /**
   * Returns the input shape to be used depending on the operation being run on the frame.
   * @return the inputted shape
   */
  ShapeDetails getInputShape();

  /**
   * Returns the position used to edit a keyframe.
   * @return position used to edit keyframe
   */
  Position2D getEditPos();

  /**
   * Returns the dimensions used to edit a keyframe.
   * @return dimensions used to edit keyframe
   */
  OurDimensions getEditDimensions();

  /**
   * Returns the color used to edit a keyframe.
   * @return color used to edit keyframe
   */
  OurColor getEditColor();

  /**
   * Returns if the shapeAdded boolean is true or false.
   * @return true if the shapeAdded boolean is true, false otherwise.
   */
  boolean getShapeAdded();

  /**
   * Returns if the shapeRemoved boolean is true or false.
   * @return true if the shapeRemoved boolean is true, false otherwise.
   */
  boolean getShapeRemoved();

  /**
   * Returns if the frameAdded boolean is true or false.
   * @return true if the frameAdded boolean is true, false otherwise.
   */
  boolean getFrameAdded();

  /**
   * Returns if the frameRemoved boolean is true or false.
   * @return true if the frameRemoved boolean is true, false otherwise.
   */
  boolean getFrameRemoved();

  /**
   * Returns if the frameEdited boolean is true or false.
   * @return true if the frameEdited boolean is true, false otherwise.
   */
  boolean getFrameEdited();
}
