package cs3500.animator.model;

/**
 * Represents a color with specific r, g, b values.
 */
public class OurColor {

  private final int r;
  private final int g;
  private final int b;

  /**
   * Creates a representation of a color with given r, g, b values.
   *
   * @param r int representing the r value of the color.
   * @param g int representing the g value of the color.
   * @param b int representing the b value of the color.
   */
  public OurColor(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid values for color inputted.");
    }

    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the r g b values of the color as a String as expected for the model.
   *
   * @return String representing the values of the position
   */
  public String getInfo() {
    return String.format("%d %d %d", this.r, this.g, this.b);
  }

  /**
   * Overrides equality to check if the colors are the same.
   *
   * @param o object being compared to this color.
   * @return true if the colors are the same, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OurColor)) {
      return false;
    }

    return (((OurColor) o).getInfo().equals(this.getInfo()));
  }

  /**
   * Overrides hashCode to fit our definition of equality.
   *
   * @return int representing the hashcode of running the getInfo method.
   */
  @Override
  public int hashCode() {
    return this.getInfo().hashCode();
  }

  public int getR() {
    return this.r;
  }

  public int getG() {
    return this.g;
  }

  public int getB() {
    return this.b;
  }

}
