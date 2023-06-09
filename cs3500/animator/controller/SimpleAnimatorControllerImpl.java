package cs3500.animator.controller;

import cs3500.animator.model.Motion;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.SimpleAnimatorModel;
import cs3500.animator.view.EditInterface;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IView;
import cs3500.animator.ViewFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the SimpleAnimatorController. Takes in a model and a view.
 *          Allows for the user to input shapes and motions and their details to update model.
 *          Makes calls that create the view and run each view as specified.
 *          Holds appendable for testing purposes.
 */
public class SimpleAnimatorControllerImpl implements SimpleAnimationController {
  private final int tickSpeed;
  private Appendable ap;

  /**
   * Constructs a SimpleAnimatorControllerImpl when given a value for its tick speed.
   *        Runs the animation, controls which view is run, and takes data from the model to be
   *        used in the view.
   *        Validity of tickSpeed and ap controlled in main method.
   * @param tickSpeed the ticks per second at which the animation should be run
   * @param ap Appendable used for testing outputs
   */
  public SimpleAnimatorControllerImpl(int tickSpeed, Appendable ap) {
    this.tickSpeed = tickSpeed;
    this.ap = ap;
  }

  @Override
  public void runAnimation(SimpleAnimatorModel model, String viewType, String outputType) {
    List<Motion> motions = new ArrayList<Motion>();

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    List<ShapeDetails> shapes = model.getShapes();

    for (ShapeDetails s : shapes) {
      motions.addAll(model.getMotions(s));
    }

    if (viewType.equals("edit")) {
      EditInterface view = new EditView();
      EditCommandInterface eci = new EditCommand(this.tickSpeed, shapes, motions);
      eci.runEdit(view);

    }
    else {
      IView view = new ViewFactory().makeView(viewType);
      ViewCommand vc = null;

      switch (viewType) {
        case "text":
          view.addShapes(shapes);
          view.setMotions(motions);
          vc = new TextualCommand(ap);
          break;
        case "visual":
          vc = new VisualCommand(tickSpeed, motions);
          break;
        case "svg":
          view.addShapes(shapes);
          view.setSpeed(tickSpeed);
          view.setMotions(motions);
          vc = new SVGCommand(ap);
          break;
        default:
          throw new IllegalArgumentException("Invalid View Type.");
      }

      view.setCanvas(model.getX(), model.getY(), model.getWidth(), model.getHeight());

      vc.runCommand(view, outputType);

    }
  }

}

