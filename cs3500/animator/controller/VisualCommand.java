package cs3500.animator.controller;

import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.view.IView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Represents a command for a VisualView. Displays and runs the visual view as expected and
 *            utilizes the speed and ticks to create the images as each tick.
 */
public class VisualCommand extends AVisualEditCommands implements ViewCommand {
  private List<Motion> motions;

  /**
   * Creates a VisualCommand when given the speed, list of shapes, and list of motions for the view.
   *                  Runs by sending each ShapeDetails state as determined by "Tweening"
   *                  for each motion.
   * @param speed ticks/second that the game will run at
   * @param motions list of motions that will be run
   */
  public VisualCommand(int speed, List<Motion> motions) {
    super(speed);
    this.motions = motions;
  }

  @Override
  public void runCommand(IView view, String outputFile) {
    int maxTick = this.getMaxTick();
    List<ShapeDetails> currentShapes;
    Timer t = new Timer();
    RunView rv;
    int count = 1;

    while (count <= maxTick) {
      currentShapes = new ArrayList<ShapeDetails>();
      for (Motion m : this.motions) {
        if (count >= m.getTick(true) && count <= m.getTick(false)) {
          currentShapes.add(this.getCurrentShape(m, count));
        }
      }

      view.addShapes(currentShapes);

      rv = new RunView(view);
      t.schedule(rv, (long) ((long) 1000 / this.speed));

      while (!rv.getDone()) {
        // this forces the code to wait until it is time for the next tick
      }

      count++;

    }
  }

  /**
   * Returns the current state of the shape based on how it is at the given tick of the motion.
   *
   * @param m     the motion that the shape belongs to
   * @param count the current count
   * @return ShapeDetails of the current state of the shape
   */
  private ShapeDetails getCurrentShape(Motion m, int count) {
    int startCount = m.getTick(true);
    int endCount = m.getTick(false);

    // each current value
    int x = this.getTweening(count, startCount, endCount,
        m.getPosition(true).getX(), m.getPosition(false).getX());
    int y = this.getTweening(count, startCount, endCount,
        m.getPosition(true).getY(), m.getPosition(false).getY());
    int width = this.getTweening(count, startCount, endCount,
        m.getShape(true).getDimension().getWidth(),
        m.getShape(false).getDimension().getWidth());
    int height = this.getTweening(count, startCount, endCount,
        m.getShape(true).getDimension().getHeight(),
        m.getShape(false).getDimension().getHeight());
    int r = this.getTweening(count, startCount, endCount,
        m.getShape(true).getColor().getR(),
        m.getShape(false).getColor().getR());
    int g = this.getTweening(count, startCount, endCount,
        m.getShape(true).getColor().getG(),
        m.getShape(false).getColor().getG());
    int b = this.getTweening(count, startCount, endCount,
        m.getShape(true).getColor().getB(),
        m.getShape(false).getColor().getB());

    Position2D p = new Position2D(x, y);
    OurDimensions d = new OurDimensions(width, height);
    OurColor c = new OurColor(r, g, b);

    return new ShapeDetails(m.getShape(true).getName(),
        OurShape.valueOf(m.getShape(true).getShapeString()), p, d, c);
  }

  @Override
  protected int getMaxTick() {
    int maxTick = 0;
    for (Motion m : motions) {
      maxTick = Integer.max(maxTick, m.getTick(false));
    }
    return maxTick;
  }
}

