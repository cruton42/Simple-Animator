package cs3500.animator.controller;

import cs3500.animator.view.EditInterface;

/**
 * Represents the command for an EditView type of view. Displays and sends information from
 *                the model to be used in the view.
 */
public interface EditCommandInterface extends ViewCommand {

  /**
   * Runs the command to output the view with an EditView.
   * @param view the view to be displayed as an EditView
   */
  void runEdit(EditInterface view);
}
