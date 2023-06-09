package cs3500.animator.controller;

import cs3500.animator.view.IView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a command for a TextualView. Outputs the textual output
 *              either to a file or to System.out as requested.
 *              Holds appendable for testing purposes.
 */
public class TextualCommand implements ViewCommand {
  private Appendable ap;

  /**
   * Constructs a TextualCommand to show the TextualView
   *            and output to either a file or to System.out as intended.
   */
  public TextualCommand(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void runCommand(IView view, String outputFile) {
    File outFile;
    FileWriter myWriter;
    String outputEnd = outputFile.substring(outputFile.length() - 3);

    switch (outputEnd) {
      case "txt":
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
