package cs3500.animator.controller;

import cs3500.animator.model.Motion;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.OurDimensions;
import cs3500.animator.model.OurShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ShapeDetails;
import cs3500.animator.model.ShapeDetailsTick;
import cs3500.animator.view.EditInterface;
import cs3500.animator.view.IView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Represents a command for a EditView. Displays and runs the edit view as expected and
 *            utilizes the speed and ticks to create the images at each tick.
 *            Allows for additional functionality based on EditView specifications.
 */
public class EditCommand extends AVisualEditCommands implements EditCommandInterface {
  private List<ShapeDetailsTick> keyFrames;
  private List<ShapeDetails> shapes;

  /**
   * Constructs an EditCommand using the given speed, shapes used, and motions. Uses this
   *          information to make keyFrames, hold a list of shapes, and work toward working with
   *          an EditView.
   * @param speed ticks per second speed of animation
   * @param shapes list of shapes being used
   * @param motions list of motions acting on those shapes
   */
  public EditCommand(int speed, List<ShapeDetails> shapes, List<Motion> motions) {
    super(speed);
    this.shapes = shapes;
    this.constructKeyFrames(shapes, motions);
  }

  /**
   * Creates and adds the list of key frames from the list of motions and its shapes.
   * @param shapes list of shapes that motions act on
   * @param motions list of motions being converted to keyframes
   */
  private void constructKeyFrames(List<ShapeDetails> shapes,
      List<Motion> motions) {
    List<ShapeDetailsTick> kf = new ArrayList<ShapeDetailsTick>();
    boolean hasFirst;

    for (ShapeDetails s : shapes) {
      hasFirst = false;
      for (Motion m : motions) {
        if (m.sameShape(s)) {
          if (!hasFirst) {
            kf.add(new ShapeDetailsTick(m.getShape(true), m.getTick(true)));
            hasFirst = true;
          }

          kf.add(new ShapeDetailsTick(m.getShape(false), m.getTick(false)));
        }
      }
    }

    this.keyFrames = kf;
  }

  @Override
  public void runEdit(EditInterface view) {
    view.setKeyFrames(this.shapes);
    int maxTick = this.getMaxTick();
    List<ShapeDetails> currentShapes = new ArrayList<ShapeDetails>();
    Timer t = new Timer();
    RunView rv;
    int count = 1;
    boolean hasFirst;
    ShapeDetailsTick first = null;
    ShapeDetailsTick next = null;

    view.setSpeed(this.speed);

    while (count <= maxTick) {

      this.checkViewChanges(view);
      this.keyFrames.sort((k1, k2) -> Integer.compare(k1.getTick(), k2.getTick()));

      this.speed = view.getSpeed();

      while (view.isPaused() || (!view.isLooping() && count >= maxTick)) {
        if (view.isRestarted()) {
          count = 1;
          view.restartRestart();
        }

        view.addShapes(currentShapes);
      }

      currentShapes = new ArrayList<ShapeDetails>();
      for (ShapeDetails sd : this.shapes) {
        hasFirst = false;
        first = null;
        next = null;

        for (int i = 0; i < this.keyFrames.size(); i++) {
          if (this.keyFrames.get(i).equals(sd)) {
            if (count >= this.keyFrames.get(i).getTick() && !hasFirst) {
              next = this.keyFrames.get(i);
              hasFirst = true;
            }
            else if (next != null && count >= next.getTick()
                && next.getTick() <= this.keyFrames.get(i).getTick() && hasFirst) {
              first = next;
              next = this.keyFrames.get(i);
            }
          }

          if (i == this.keyFrames.size() - 1
              && (next != null && (first == null || count > next.getTick()))) {
            currentShapes.add(next.updateShape(null, null, null));
            hasFirst = false;
          }
          else if (i == this.keyFrames.size() - 1 && first != null && next != null) {
            currentShapes.add(this.getCurrentShape(first, next, count));
          }

        }
      }

      view.addShapes(currentShapes);

      rv = new RunView(view);
      t.schedule(rv, (long) ((long) 1000 / this.speed));

      while (!rv.getDone()) {
        // this forces the code to wait until it is time for the next tick
      }

      if (view.isRestarted()) {
        count = 1;
        view.restartRestart();
      }

      if ((view.isLooping() && count >= maxTick)) {
        count = 1;
      } else {
        count++;
      }
    }
  }

  /**
   * Checks if any changes have been made to the view (frames or shapes), and makes changes
   *                      to the command accordingly.
   * @param view the view in question that is being checked
   */
  private void checkViewChanges(EditInterface view) {
    if (view.getInputFrame() != null) {
      if (view.getFrameAdded()) {
        this.addKeyFrame(view.getInputFrame());
        view.resetInputBooleans();
      }
      else if (view.getFrameRemoved()) {
        this.removeKeyFrame(view.getInputFrame());
        view.resetInputBooleans();
      }
      else if (view.getFrameEdited()) {
        this.editKeyFrame(view.getInputFrame(), view.getEditPos(),
            view.getEditDimensions(), view.getEditColor());
        view.resetInputBooleans();
      }
    }
    else if (view.getShapeAdded()) {
      if (!this.shapes.contains(view.getInputShape())) {
        this.shapes.add(view.getInputShape());
        view.setKeyFrames(this.shapes);
      }
      view.resetInputBooleans();
    }
    else if (view.getShapeRemoved()) {
      if (this.shapes.contains(view.getInputShape())) {
        this.shapes.remove(view.getInputShape());
        view.setKeyFrames(this.shapes);
      }
      view.resetInputBooleans();
    }
  }

  /**
   * Adds a keyframe to the list as expected.
   * @param frame keyframe to be added
   */
  private void addKeyFrame(ShapeDetailsTick frame) {
    int count = frame.getTick();
    ShapeDetailsTick first;
    ShapeDetailsTick next;
    boolean hasFirst;
    hasFirst = false;
    first = null;
    next = null;

    for (int i = 0; i < this.keyFrames.size(); i++) {
      if (this.keyFrames.get(i).equals(frame)) {
        if (count >= this.keyFrames.get(i).getTick() && !hasFirst) {
          next = this.keyFrames.get(i);
          hasFirst = true;
        }
        else if (count >= next.getTick() && next.getTick() <= this.keyFrames.get(i).getTick()
            && hasFirst) {
          first = next;
          next = this.keyFrames.get(i);
        }
      }
    }
    if (first != null) {
      this.keyFrames.add(new ShapeDetailsTick(this.getCurrentShape(first, next, count), count));
    }
    else if (next != null) {
      this.keyFrames.add(new ShapeDetailsTick(next.updateShape(null, null, null),
          frame.getTick()));
    }
    else {
      this.keyFrames.add(frame);
    }
  }

  /**
   * Removes a key frame from the list as expected.
   * @param frame keyframe to be removed
   */
  private void removeKeyFrame(ShapeDetailsTick frame) {
    for (int i = 0; i < this.keyFrames.size(); i++) {
      if (this.keyFrames.get(i).equals(frame)
          && this.keyFrames.get(i).getTick() == frame.getTick()) {
        this.keyFrames.remove(i);
      }
    }
  }

  /**
   * Edits a keyframe as expected.
   * @param frame keyframe to be edited
   * @param changePos change in position
   * @param changeDim change in dimension
   * @param changeCol change in color
   */
  private void editKeyFrame(ShapeDetailsTick frame, Position2D changePos,
      OurDimensions changeDim, OurColor changeCol) {
    for (int i = 0; i < this.keyFrames.size(); i++) {
      if (frame.equals(this.keyFrames.get(i))
          && this.keyFrames.get(i).getTick() == frame.getTick()) {
        this.keyFrames.set(i, new ShapeDetailsTick(
            this.keyFrames.get(i).updateShape(changePos, changeDim, changeCol), frame.getTick()));
      }
    }
  }

  /**
   * Returns the current state of the shape based on how it is at the given tick of the animation.
   *
   * @param first the keyframe of the start of the animation
   * @param next the keyframe of the end of the animation
   * @param count the current count
   * @return ShapeDetails of the current state of the shape
   */
  private ShapeDetails getCurrentShape(ShapeDetailsTick first, ShapeDetailsTick next, int count) {
    int startCount = first.getTick();
    int endCount = next.getTick();

    // each current value
    int x = this.getTweening(count, startCount, endCount,
        first.getPos().getX(),
        next.getPos().getX());
    int y = this.getTweening(count, startCount, endCount,
        first.getPos().getY(),
        next.getPos().getY());
    int width = this.getTweening(count, startCount, endCount,
        first.getDimension().getWidth(),
        next.getDimension().getWidth());
    int height = this.getTweening(count, startCount, endCount,
        first.getDimension().getHeight(),
        next.getDimension().getHeight());
    int r = this.getTweening(count, startCount, endCount,
        first.getColor().getR(),
        next.getColor().getR());
    int g = this.getTweening(count, startCount, endCount,
        first.getColor().getG(),
        next.getColor().getG());
    int b = this.getTweening(count, startCount, endCount,
        first.getColor().getB(),
        next.getColor().getB());

    Position2D p = new Position2D(x, y);
    OurDimensions d = new OurDimensions(width, height);
    OurColor c = new OurColor(r, g, b);

    ShapeDetails s = new ShapeDetails(first.getName(),
        OurShape.valueOf(first.getShapeString()), p, d, c);

    return new ShapeDetails(first.getName(),
        OurShape.valueOf(first.getShapeString()), p, d, c);
  }

  @Override
  protected int getMaxTick() {
    int maxTick = 0;
    for (ShapeDetailsTick sdt : this.keyFrames) {
      maxTick = Integer.max(maxTick, sdt.getTick());
    }
    return maxTick;
  }

  @Override
  public void runCommand(IView view, String outputFile) {
    throw new UnsupportedOperationException("Unsupported operation from the EditCommand");
  }
}
