package cs3500.animator.controller;

import cs3500.animator.view.IView;

/**
 * Represents the different types of commands and how they should be applied to work with each
 *                  kind of view. Runs the view as intended, sending to a file or to System.out,
 *                  or showing the view in the case of the VisualView.
 */
public interface ViewCommand {

  /**
   * Runs the command to work with the view. Shows the view or outputs to file as specified.
   * @param view the view being used in the command.
   * @param outputFile String representing the name of the file being created.
   */
  public void runCommand(IView view, String outputFile);
}


