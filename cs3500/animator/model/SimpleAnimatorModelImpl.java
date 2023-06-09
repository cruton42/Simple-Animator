package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for a simple animator. Contains array of the shapes, and ArrayList of the motions
 *          on those shapes, x and y values of the corners, width of the screen and height of the
 *          screen. Allows for adding shapes and motions, displaying details, etc.
 */
public class SimpleAnimatorModelImpl implements SimpleAnimatorModel {

  private List<ShapeDetails> shapes;
  private List<Motion> motions;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructs a SimpleAnimatorModelImpl object given the list of ShapeDetails to be used.
   * Invariants: Array of shapes is not null, contains no null shapes. All invariants of
   * ShapeDetails remain true for the shapes in the array.
   *
   * @param shapes List of ShapeDetails representing all of the shapes that can be operated on.
   * @throws IllegalArgumentException if the array of shapes is null or any of the shapes in it is
   *                                  null.
   */
  public SimpleAnimatorModelImpl(List<ShapeDetails> shapes) {
    this(shapes, new ArrayList<Motion>());
  }

  /**
   * Constructs a SimpleAnimatorModelImpl object given the list of ShapeDetails to be used and the
   * ArrayList of motions.
   * Invariants: Arrays are not null, contains no null shapes. All invariants of ShapeDetails
   *              remain
   * true for the shapes in the array. All of the adjacent motions for each shape line up (no
   * "teleportation" of position or color).
   *
   * @param shapes  List of ShapeDetails representing all of the shapes that can be operated on.
   * @param motions ArrayList of Motions representing all of the motions working on the shapes.
   * @throws IllegalArgumentException if the array of shapes is null or any of the shapes in it is
   *                                  null or if adjacent motions do not line up.
   */
  public SimpleAnimatorModelImpl(List<ShapeDetails> shapes, List<Motion> motions) {

    if (shapes == null || motions == null) {
      throw new IllegalArgumentException("Given Arrays and ArrayLists cannot be null.");
    }

    for (ShapeDetails s : shapes) {
      if (s == null) {
        throw new IllegalArgumentException("Array of shapes cannot have null shapes.");
      }
    }

    for (Motion m : motions) {
      if (m == null) {
        throw new IllegalArgumentException("ArrayList of Motions cannot have null Motions.");
      }
    }

    this.shapes = shapes;
    this.motions = motions;
    this.x = 0;
    this.y = 0;
    this.width = 0;
    this.height = 0;

    this.sortMotionList();

    // checks that the motions do not involve "teleportation"
    Motion m = null;
    for (int i = 0; i < shapes.size(); i++) {
      m = null;
      for (int j = 0; j < motions.size(); j++) {

        if (motions.get(j).sameShape(shapes.get(i))) {
          if (m == null) {
            m = motions.get(j);
          } else {
            if (motions.get(j).doesOverlap(m.getTick(true), m.getTick(false))) {
              throw new IllegalArgumentException("There cannot be overlap of motions.");
            }
            if (!(motions.get(j).getPosition(true).equals(m.getPosition(false)))) {
              throw new IllegalArgumentException("Positions of adjacent moves must line up.");
            }
            if (!(motions.get(j).getColor(true).equals(m.getColor(false)))) {
              throw new IllegalArgumentException("Colors of adjacent moves must line up.");
            }

            m = motions.get(j);
          }
        }
      }
    }
  }

  @Override
  public void addMotion(ShapeDetails shape, int start, int end,
      Position2D startPos, OurColor startCol, Position2D toPos,
      OurDimensions toDim, OurColor colorChange) {

    if (!(this.shapes.contains(shape)) || shape == null) {
      throw new IllegalArgumentException("Given shape must be an existing shape.");
    }

    if (toPos == null && colorChange == null) {
      throw new IllegalArgumentException("Must change either position or color for valid Motion.");
    } else if (toPos == null) {
      motions.add(new Motion(
          new ShapeDetails(shape.getName(), OurShape.valueOf(shape.getShapeString()),
              startPos, shape.getDimension(), startCol), start, end, colorChange));
    } else if (colorChange == null) {
      motions.add(new Motion(
          new ShapeDetails(shape.getName(), OurShape.valueOf(shape.getShapeString()),
          startPos, shape.getDimension(), startCol), start, end, toPos));
    } else {
      motions.add(new Motion(
          new ShapeDetails(shape.getName(), OurShape.valueOf(shape.getShapeString()),
          startPos, shape.getDimension(), startCol), start, end, toPos, toDim, colorChange));
    }

    this.sortMotionList();
  }

  @Override
  public void addShape(ShapeDetails shape) {
    if (shape == null) {
      throw new IllegalArgumentException("You can't add a null shape!");
    }
    this.shapes.add(shape);
  }

  /**
   * Sorts the list from first start to last start.
   */
  private void sortMotionList() {
    this.motions.sort((m1, m2) -> Integer.compare(m1.getTick(true), m2.getTick(true)));
  }

  @Override
  public List<Motion> getMotions(ShapeDetails shape) {
    List<Motion> result = new ArrayList<Motion>();
    for (Motion m : motions) {
      if (m.sameShape(shape)) {
        result.add(m);
      }
    }
    return result;
  }

  @Override
  public List<ShapeDetails> getShapes() {
    List<ShapeDetails> temp = new ArrayList<ShapeDetails>(this.shapes);
    return temp;
  }

  @Override
  public void setConstraints(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Builder class to build SimpleAnimatorModelImpl objects. Also used with AnimationReader.
   */
  public static final class Builder implements AnimationBuilder<SimpleAnimatorModel> {
    private List<ShapeDetails> shapes;
    private List<Motion> motions;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Creates a Builder for the SimpleAnimatorModel. Initializes the Lists.
     */
    public Builder() {
      this.shapes = new ArrayList<ShapeDetails>();
      this.motions = new ArrayList<Motion>();
    }

    @Override
    public SimpleAnimatorModel build() {
      SimpleAnimatorModel sam = new SimpleAnimatorModelImpl(this.shapes, this.motions);
      sam.setConstraints(this.x, this.y, this.width, this.height);

      return sam;
    }

    @Override
    public AnimationBuilder<SimpleAnimatorModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;

      return this;
    }

    @Override
    public AnimationBuilder<SimpleAnimatorModel> declareShape(String name, String type) {
      OurShape shape;

      if (name == null || type == null) {
        throw new IllegalArgumentException("name or type cannot be null");
      }

      shape = OurShape.valueOf(type.toUpperCase());

      this.shapes.add(new ShapeDetails(name, shape, new Position2D(0,0),
          new OurDimensions(1,1), new OurColor(0,0,0)));

      return this;
    }

    @Override
    public AnimationBuilder<SimpleAnimatorModel> addMotion(String name, int t1, int x1, int y1,
        int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {

      OurShape os = null;

      for (ShapeDetails s : this.shapes) {
        if (s.getName().equals(name)) {
          os = OurShape.valueOf(s.getShapeString());
        }
      }

      if (os == null) {
        throw new IllegalArgumentException("No shape exists with inputted name.");
      }

      this.motions.add(new Motion(new ShapeDetails(name, os, new Position2D(x1,y1),
          new OurDimensions(w1,h1), new OurColor(r1, g1, b1)),
          t1, t2, new Position2D(x2, y2), new OurDimensions(w2, h2), new OurColor(r2, g2, b2)));

      return this;
    }

    @Override
    public AnimationBuilder<SimpleAnimatorModel> addKeyframe(String name, int t, int x, int y,
        int w, int h, int r, int g, int b) {
      return null;
    }
  }

}
