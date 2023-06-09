package cs3500.animator.model;

/**
 * This class represents a 2D position. Class provided in lecture, with some adjustment.
 */
public final class Position2D {

  private final int x;
  private final int y;

  /**
   * Creates a position for a shape given the x and y value. Invariants: x and y must be
   * non-negative ints.
   *
   * @param x int representing the x value.
   * @param y int representing the x value.
   */
  public Position2D(int x, int y) {

    this.x = x;
    this.y = y;
  }

  /**
   * Returns the position as a String as expected for the model.
   *
   * @return String representing the x and y values of the position
   */
  public String getInfo() {
    return String.format("%d %d", this.x, this.y);
  }

  /**
   * Overrides equality to check if the positions are the same.
   *
   * @param o object being compared to this position.
   * @return true if the positions are the same, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Position2D)) {
      return false;
    }

    return (((Position2D) o).getInfo().equals(this.getInfo()));
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

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}
