package cs3500.animator;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualView;

/**
 * Factory to create all of the possible different types of Views. One method to make a View.
 */
public class ViewFactory {

  /**
   * Creates a ViewFactor to make a view.
   */
  public ViewFactory() {
    // not necessary since creation requires no parameters
  }

  /**
   * Makes a view based on the type of view inputted as a String.
   * @param viewType represents the type of view
   * @return a IView representing the intended view
   */
  public IView makeView(String viewType) {
    IView result = null;
    switch (viewType) {
      case "text":
        result = new TextualView();
        break;
      case "visual":
        result = new VisualView();
        break;
      case "svg":
        result = new SVGView();
        break;
      default:
        throw new IllegalArgumentException("You didn't pick a valid view type.");
    }
    return result;
  }
}
