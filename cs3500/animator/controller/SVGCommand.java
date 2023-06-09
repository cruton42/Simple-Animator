package cs3500.animator.controller;

import cs3500.animator.view.IView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a command regarding an SVG. Outputs the SVG to either a file of
 *              the specified name or to System.out.
 *              Holds appendable for testing purposes.
 */
public class SVGCommand implements ViewCommand {
  private Appendable ap;

  /**
   * Constructs an SVGCommand to run/create a file for a SVG view based on the specified
   *              SVG style formatting.
   */
  public SVGCommand(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void runCommand(IView view, String outputFile) {
    File outFile;
    FileWriter myWriter;
    String outputEnd = outputFile.substring(outputFile.length() - 3);

    switch (outputEnd) {
      case "svg":
        outFile = new File(outputFile);
        myWriter = null;
        try {
          myWriter = new FileWriter(outputFile);
          myWriter.write(view.getTextualRepresentation());
          myWriter.flush();
          myWriter.close();
        }
        catch (IOException e) {
          throw new IllegalStateException("Something wrong with the writer.");
        }
        break;
      case "out":
        System.out.print(view.getTextualRepresentation());
        break;
      case "tst":
        // case for testing purposes
        try {
          ap.append(view.getTextualRepresentation());
        }
        catch (IOException ioe) {
          throw new IllegalStateException("Appendable failed");
        }
        break;
      default:
        throw new IllegalArgumentException("You didn't pick a valid view type.");
    }
  }
}
