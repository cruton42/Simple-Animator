package cs3500.animator.controller;

import cs3500.animator.model.SimpleAnimatorModel;

/**
 * Interface that represents the controller.
 *           Allows for the user to input shapes and motions and their details to update model.
 *           Makes calls that create the view on each tick if necessary.
 */
public interface SimpleAnimationController {

  /**
   * Runs the animation, and creates the output either to System.out or to the required document.
   * @param model model to be used for the data
   * @param viewType ViewType representing the type of view to be created
   * @throws IllegalArgumentException if the model is null, if the viewType is not valid,
   *                                  or if the outputType is not valid.
   */
  void runAnimation(SimpleAnimatorModel model, String viewType, String outputType);

}
