import cs3500.animator.model.Position2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for Position2D class and its methods.
 */
public class PositionTests {

  @Test
  public void testPositionConstructor() {
    Position2D p = new Position2D(5, 0);
    assertEquals("5 0", p.getInfo());
  }

  @Test
  public void testGetInfo() {
    Position2D p = new Position2D(25, 25);
    Position2D p2 = new Position2D(25, 25);
    Position2D p3 = new Position2D(42, 0);

    // checks getInfo results
    assertEquals("25 25", p.getInfo());
    assertEquals("25 25", p2.getInfo());
    assertEquals("42 0", p3.getInfo());

    // tests that same dimensions give same getInfo results
    assertFalse(p3.getInfo().equals(p2.getInfo()));
    assertEquals(p.getInfo(), p2.getInfo());

  }

}
