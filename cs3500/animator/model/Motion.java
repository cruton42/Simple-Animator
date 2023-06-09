package cs3500.animator.model;

/**
 * Class representing a motion to be animated. Contains and operates on information such as the
 * initial shape, the end shape, the start tick, and the end tick. Assumes that the user does not
 * give any different shapes the same name.
 */
public final class Motion {

  private ShapeDetails shape;
  private ShapeDetails endShape;
  private final int start;
  private final int end;

  /**
   * Constructs a motion with a shape to be moved, a position to move to, a starting and ending
   * tick, and a color to change to. Invariants: Maintained through the ShapeDetails, Position2D,
   * and OurColor constructors - The x and y values of the positions are non-negative. r, g, and b
   * are ints in 0 to 255 inclusive. The width and height of the dimensions are positive. start and
   * end are non-negative. start < end.
   *
   * @param shape       shape to be moved
   * @param start       tick to start the motion
   * @param end         tick on which the motion will end
   * @param toPos       ending position of the shape
   * @param toDim       ending dimensions of the shape
   * @param colorChange color the shape will change to
   * @throws IllegalArgumentException if the start or end values are negative or if start is greater
   *                                  than or equal to end, or if the given ShapeDetails is null
   */
  public Motion(ShapeDetails shape, int start, int end, Position2D toPos, OurDimensions toDim,
      OurColor colorChange) {
    if (shape == null) {
      throw new IllegalArgumentException("ShapeDetails cannot be null.");
    }

    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("Start and end values must be non-negative.");
    }

    if (start > end) {
      throw new IllegalArgumentException("Start value must be less than end value.");
    }

    this.shape = shape;
    this.start = start;
    this.end = end;
    this.endShape = shape.updateShape(toPos, toDim, colorChange);
  }

  /**
   * Constructs a motion with a shape to be moved, a position to move to, and a starting and ending
   * tick. Invariants: Maintained through the ShapeDetails, Position2D, and OurColor constructors -
   * The x and y values of the positions are non-negative. r, g, and b are ints in 0 to 255
   * inclusive. The width and height of the dimensions are positive. start and end are
   * non-negative.
   *
   * @param shape shape to be moved
   * @param toPos ending position of the shape
   * @param start tick to start the motion
   * @param end   tick on which the motion will end
   */
  public Motion(ShapeDetails shape, int start, int end, Position2D toPos) {
    this(shape, start, end, toPos, null, null);
  }

  /**
   * Constructs a motion representing a color change with a shape to be moved, a position to move
   * to, and a starting and ending tick. Invariants: Maintained through the ShapeDetails,
   * Position2D, and OurColor constructors - The x and y values of the positions are non-negative.
   * r, g, and b are ints in 0 to 255 inclusive. The width and height of the dimensions are
   * positive. start and end are non-negative.
   *
   * @param shape       shape to be moved
   * @param start       tick to start the motion
   * @param end         tick on which the motion will end
   * @param colorChange color to be changed to
   */
  public Motion(ShapeDetails shape, int start, int end, OurColor colorChange) {
    this(shape, start, end, null, null, colorChange);
  }

  /**
   * Returns whether or not this motion overlaps with the given shape, starting or ending tick.
   * Assumes that the shapes are the same, only run when the shapes are.
   *
   * @param start starting tick of the given motion
   * @param end   ending tick of the given motion
   * @return true if the given inputs do overlap with this motion, false otherwise
   */
  public boolean doesOverlap(int start, int end) {

    return (this.start < start && this.end > start)
        || (this.start < end && this.end > end);
  }

  /**
   * Returns whether or not the provided ShapeDetails is equal to the ShapeDetails of the Motion.
   *
   * @param s ShapeDetails being compared to the shape in motion
   * @return true if the given ShapeDetails is equal to the shape in motion, false otherwise
   */
  public boolean sameShape(ShapeDetails s) {
    return this.shape.equals(s);
  }

  /**
   * Returns an appropriate String representing the information of the Motion.
   *
   * @return a String representing the info of the initial and ending shape and the ticks
   */
  public String getInfo() {
    return "motion " + this.shape.getName() + " " + start + " " + this.shape.getInfo() + "   "
        + this.end + " " + this.endShape.getInfo();
  }

  /**
   * Returns the start tick or end tick to be used to sort by start.
   *
   * @param start boolean that represents if the tick should be the start tick (or end tick).
   * @return the requested tick.
   */
  public int getTick(boolean start) {
    if (start) {
      return this.start;
    } else {
      return this.end;
    }
  }


  /**
   * Returns the position of the Motion, either at the beginning or at the end depending on the
   * provided boolean.
   *
   * @param first boolean representing whether the position should be of the first or end position.
   * @return Position2D representing the position of the motion.
   */
  public Position2D getPosition(boolean first) {
    Position2D temp = null;

    if (first) {
      temp = this.shape.getPos();
    } else {
      temp = this.endShape.getPos();
    }

    return temp;
  }


  /**
   * Returns the color of the Motion, either at the beginning or at the end depending on the
   * provided boolean.
   *
   * @param first boolean representing whether the color should be of the first or end color.
   * @return Position2D representing the color of the motion.
   */
  public OurColor getColor(boolean first) {
    if (first) {
      return this.shape.getColor();
    } else {
      return this.endShape.getColor();
    }
  }

  /**
   * Returns the ShapeDetails object, either the starting or ending shape depending on the boolean.
   * @param first boolean determining whether the first or last shape should be returned
   * @return either the starting or ending shape, depending on the boolean
   */
  public ShapeDetails getShape(boolean first) {
    ShapeDetails result;
    if (first) {
      result = this.shape;
    }
    else {
      result = this.endShape;
    }

    return result;
  }
}
