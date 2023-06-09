package cs3500.animator.controller;

import cs3500.animator.view.IView;
import java.util.TimerTask;

/**
 * Abstract class representing visual and edit commands (which have multiple similar classes
 *                        and implementation).
 */
public abstract class AVisualEditCommands implements ViewCommand {
  protected int speed;

  /**
   * Constructs an AVisualEditCommands object.
   * @param speed int representing the speed of the animation
   */
  public AVisualEditCommands(int speed) {
    this.speed = speed;
  }

  /**
   * Returns the int value of the specific value at the specific time based on the given "Tweening"
   * function.
   *
   * @param count      the current tick count
   * @param startCount the starting tick count
   * @param endCount   the ending tick count
   * @param startVal   the initial value
   * @param endVal     the ending value
   * @return the value based on the tweening equation
   */
  protected int getTweening(int count, int startCount, int endCount, int startVal, int endVal) {
    return (int) (.5 + (((double) startVal * (((double) endCount - (double) count)
        / ((double) endCount - (double) startCount)))
        + ((double) endVal * (((double) count - (double) startCount)
        / ((double) endCount - (double) startCount)))));
  }

  /**
   * Gets the max tick in the animation.
   *
   * @return the max tick
   */
  abstract protected int getMaxTick();

  /**
   * TimerTask class used to run the view after a specified amount of time,
   *          as dictated by the speed.
   */
  protected class RunView extends TimerTask {
    private boolean done;
    private IView view;

    /**
     * Creates a RunView with the specific view, and initializes done to false.
     * @param view IView that the RunView will show
     */
    RunView(IView view) {
      this.view = view;
      this.done = false;
    }

    @Override
    public void run() {
      this.view.refresh();
      this.done = true;
    }

    /**
     * Returns whether or not the run method has been run or not.
     *
     * @return true if the run method has been run and done is true, false otherwise
     */
    public boolean getDone() {
      return this.done;
    }
  }

}
