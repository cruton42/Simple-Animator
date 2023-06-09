package cs3500.animator.model;

/**
 * Represents the details of a shape, including its type of shape, position, dimensions, and color.
 * Assumes that the user does not give any shape the same name.
 */
public class ShapeDetails {

  private String name;
  private OurShape shape;
  private Position2D pos;
  private OurDimensions dim;
  private OurColor color;

  /**
   * Constructs an object representing a shape with its type of shape, x and y location of the
   * center, width and height, and r,g,b values of its color. Invariants: The x and y values of the
   * position are non-negative. r, g, and b are ints in 0 to 255 inclusive. The width and height of
   * the dimensions are positive.
   *
   * @param name  String representing the name of the shape.
   * @param shape OurShape representing the type of shape.
   * @param pos   Position2D representing the position of the shape.
   * @param dim   OurDimensions representing the dimensions of the shape.
   * @param color OurColor representing the color of the shape.
   * @throws IllegalArgumentException if any of the parameters are invalid (invariants violated) or
   *                                  null.
   */
  public ShapeDetails(String name, OurShape shape,
      Position2D pos, OurDimensions dim, OurColor color) {
    if (name == null || shape == null || pos == null || dim == null || color == null) {
      throw new IllegalArgumentException("Details cannot be null.");
    }

    this.name = name;
    this.shape = shape;
    this.pos = pos;
    this.dim = dim;
    this.color = color;
  }

  /**
   * Returns the values of the position, dimensions, and color of the shape as a String.
   *
   * @return String representing the information of the shape.
   */
  public String getInfo() {
    return pos.getInfo() + " " + dim.getInfo() + " " + color.getInfo();
  }

  /**
   * Returns the type of the shape as a String.
   *
   * @return String representing the name of the type of shape.
   */
  public String getShapeString() {
    return this.shape.toString();
  }

  /**
   * Returns the given name of the shape as a String.
   *
   * @return String representing the name of shape.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns a shape with the updated values for position or color. All invariants are maintained
   * since the toPos and colorChange are maintained by their respective classes.
   *
   * @param toPos       the new position to be changed, or null if there is no new position
   * @param colorChange the new color to be changed, or null if there is no new color
   * @return ShapeDetails with the same fields as the initial object, but possible changes to
   *         position or color.
   */
  public ShapeDetails updateShape(Position2D toPos, OurDimensions toDim, OurColor colorChange) {
    OurDimensions newDim;
    Position2D newPos;
    OurColor newColor;

    if (toDim == null) {
      newDim = this.dim;
    }
    else {
      newDim = toDim;
    }

    if (toPos == null) {
      newPos = this.pos;
    } else {
      newPos = toPos;
    }

    if (colorChange == null) {
      newColor = this.color;
    } else {
      newColor = colorChange;
    }

    return new ShapeDetails(this.name, this.shape, newPos, newDim, newColor);
  }

  /**
   * Returns the position of the shape.
   *
   * @return position of the shape.
   */
  public Position2D getPos() {
    Position2D temp = this.pos;
    return temp;
  }

  /**
   * Returns the color of the shape.
   *
   * @return color of the shape.
   */
  public OurColor getColor() {
    OurColor temp = this.color;
    return temp;
  }

  /**
   * Overrides equals method to determine equality by same name and shape, which is how we define
   * sameness.
   *
   * @param o Object being compared to this ShapeDetails.
   * @return true if the ShapeDetails have the same name and shape, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ShapeDetails)) {
      return false;
    }

    return (((ShapeDetails) o).getName().equals(this.name)
        && (((ShapeDetails) o).getShapeString().equals(this.getShapeString())));
  }

  /**
   * Overrides the hashCode method to represent equality as we define it.
   *
   * @return int representing the hashCode of the ShapeDetails object.
   */
  @Override
  public int hashCode() {
    String name = this.name;
    return name.hashCode();
  }


  public OurDimensions getDimension() {
    return this.dim;
  }
}
