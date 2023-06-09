package cs3500.animator.model;

/**
 * Represents a ShapeDetails with a given tick. Essentially used to represent a keyframe.
 */
public class ShapeDetailsTick extends ShapeDetails {
  private int tick;

  /**
   * Constructs an object representing a shape with its type of shape, x and y
   *          location of the center,
   *          width and height, and r,g,b values of its color.
   * Invariants: The x and y values of the position
   *          are non-negative. r, g, and b are ints in 0 to 255 inclusive.
   *          The width and height of the dimensions are positive.
   *
   * @param name  String representing the name of the shape.
   * @param shape OurShape representing the type of shape.
   * @param pos   Position2D representing the position of the shape.
   * @param dim   OurDimensions representing the dimensions of the shape.
   * @param color OurColor representing the color of the shape.
   * @throws IllegalArgumentException if any of the parameters are invalid (invariants violated) or
   *                                  null.
   */
  public ShapeDetailsTick(String name, OurShape shape, Position2D pos,
      OurDimensions dim, OurColor color, int tick) {
    super(name, shape, pos, dim, color);
    this.tick = tick;
  }

  public ShapeDetailsTick(ShapeDetails sd, int tick) {
    this(sd.getName(), OurShape.valueOf(sd.getShapeString()), sd.getPos(),
        sd.getDimension(), sd.getColor(), tick);
  }

  /**
   * Returns the tick of the given ShapeDetailsTick (or keyframe).
   * @return int representing the given tick
   */
  public int getTick() {
    return this.tick;
  }
}
