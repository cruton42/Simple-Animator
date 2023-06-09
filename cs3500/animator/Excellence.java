package cs3500.animator;

import cs3500.animator.controller.SimpleAnimationController;
import cs3500.animator.controller.SimpleAnimatorControllerImpl;
import cs3500.animator.model.SimpleAnimatorModel;
import cs3500.animator.model.SimpleAnimatorModelImpl.Builder;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Takes in the command-line arguments in order to run all of the Shape motion animations
 *            and output as expected.
 *            EXCELLENCE.
 */
public final class Excellence {

  /**
   * Main method to run the Shape animations.
   * @param args String[] representing the command-line arguments
   */
  public static void main(String[] args) {
    SimpleAnimationController controller;
    SimpleAnimatorModel model;
    AnimationReader animationReader = new AnimationReader();
    AnimationBuilder<SimpleAnimatorModel> animationBuilder = new Builder();

    Readable fileIn = null;
    String viewType = "";
    String outputType = "";
    int ticksPerSecond = -1;
    boolean haveViewType = false;
    boolean haveFileName = false;
    boolean haveOutputType = false;
    boolean haveSpeed = false;

    JFrame errorFrame = new JFrame();
    errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    boolean errorThrown = false;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          try {
            fileIn = new FileReader(args[i + 1]);
          }
          catch (FileNotFoundException e) {
            errorThrown = true;
          }

          haveFileName = true;
          i++;
          break;

        case "-view":
          viewType = args[i + 1];
          haveViewType = true;
          i++;
          break;

        case "-out":
          outputType = args[i + 1];

          haveOutputType = true;
          i++;
          break;

        case "-speed":
          try {
            ticksPerSecond = Integer.parseInt(args[i + 1]);
          }
          catch (final NumberFormatException e) {
            errorThrown = true;
            break;
          }

          if (ticksPerSecond < 0) {
            errorThrown = true;
          }
          haveSpeed = true;
          i++;
          break;

        default:
          errorThrown = true;
      }
    }

    if (!haveOutputType && !haveSpeed) {
      outputType = "system.out";
      ticksPerSecond = 1;
    }
    else if (!haveOutputType) {
      outputType = "system.out";
    }
    else if (!haveSpeed) {
      ticksPerSecond = 1;
    }

    if (!(outputType.substring(outputType.length() - 3).equals("svg")
        || outputType.substring(outputType.length() - 3).equals("txt")
        || outputType.substring(outputType.length() - 3).equals("out"))) {
      errorThrown = true;
    }

    if (!haveFileName || !haveViewType || errorThrown) {
      JOptionPane errorPane = new JOptionPane();
      errorPane.showMessageDialog(errorFrame,
          "One or more command-line inputs are invalid",
          "Invalid Input",
          JOptionPane.WARNING_MESSAGE);
      errorFrame.dispose();

      throw new IllegalArgumentException("Illegal Command-Line Input");
    }

    model = animationReader.parseFile(fileIn, animationBuilder);

    controller = new SimpleAnimatorControllerImpl(ticksPerSecond, new StringBuilder());

    controller.runAnimation(model, viewType, outputType);
  }
}