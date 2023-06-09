package cs3500.animator.model;

/**
 * Represents the specific dimensions with width and height.
 */
public class OurDimensions {

  private int width;
  private int height;

  /**
   * Creates an object representing dimensions with width and height. Invariants: width and height
   * must be positive ints.
   *
   * @param width  int representing the width of the shape.
   * @param height int representing the height of the shape.
   * @throws IllegalArgumentException if the width or height are not positive.
   */
  public OurDimensions(int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid height or width.");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the dimensions as a String as expected for the model.
   *
   * @return String representing the values of the position
   */
  public String getInfo() {
    return String.format("%d %d", this.width, this.height);
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

}
