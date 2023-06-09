import cs3500.animator.model.OurColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for the OurColor class and its methods.
 */
public class ColorTests {

  @Test
  public void testConstructor() {
    OurColor c = new OurColor(1, 222, 57);
    assertEquals("1 222 57", c.getInfo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooLowValue() {
    OurColor c = new OurColor(-25, 222, 57);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeValueLast() {
    OurColor c = new OurColor(25, 10, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooHighValue() {
    OurColor c = new OurColor(34, 256, 57);
  }

  @Test
  public void testGetInfo() {
    OurColor c = new OurColor(25, 0, 255);
    OurColor c2 = new OurColor(100, 1, 0);
    OurColor c3 = new OurColor(25, 0, 255);

    // checks getInfo results
    assertEquals("25 0 255", c.getInfo());
    assertEquals("100 1 0", c2.getInfo());
    assertEquals("25 0 255", c3.getInfo());

    // tests that same dimensions give same getInfo results
    assertFalse(c3.getInfo().equals(c2.getInfo()));
    assertEquals(c.getInfo(), c3.getInfo());

  }

}
