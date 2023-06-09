package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.ShapeDetails;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Represents a visual view, which shows the shapes moving as images visually.
 */
public class VisualView extends JFrame implements IView {

  private VisualPanel visualPanel;
  private int width;
  private int height;

  /**
   * Constructs a VisualView. Adds a VisualPanel to add the shapes and a scroll bar to allow
   *            the user to scroll across the panel as necessary.
   */
  public VisualView() {
    super("VisualView");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    this.visualPanel = new VisualPanel();

    this.width = 0;
    this.height = 0;

    //Makes scroll bar
    JScrollPane scroll = new JScrollPane(visualPanel);
    scroll.getVerticalScrollBar().setUnitIncrement(16);
    scroll.getHorizontalScrollBar().setUnitIncrement(16);


    this.add(scroll);

    setVisible(true);
    pack();
  }

  @Override
  public void addShapes(List<ShapeDetails> shapes) {
    this.visualPanel.setShapes(shapes);
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.width = width;
    this.height = height;
    this.visualPanel.setCanvas(x, y, width, height);
  }

  @Override
  public void setMotions(List<Motion> motions) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported operation for VisualView");
  }

  @Override
  public Dimension getPreferredSize() {
    // does not run in typical use, only for tests primarily
    if (width <= 0 || height <= 0) {
      return new Dimension(1000,1000);
    }

    return new Dimension(this.width, this.height);
  }

  @Override
  public void setSpeed(int speed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported operation for VisualView");
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public String getTextualRepresentation() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported operation for VisualView");
  }

}
