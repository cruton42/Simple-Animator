import cs3500.animator.model.OurDimensions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for the OurDimensions class and its methods.
 */
public class DimensionsTests {

  @Test
  public void testConstructor() {
    OurDimensions od = new OurDimensions(2, 3);
    assertEquals("2 3", od.getInfo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeValueConstructor() {
    OurDimensions od = new OurDimensions(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    OurDimensions od = new OurDimensions(15, -1);
  }

  @Test
  public void testGetInfo() {
    OurDimensions od = new OurDimensions(3, 5);
    OurDimensions od2 = new OurDimensions(2, 3);
    OurDimensions od3 = new OurDimensions(3, 5);

    // checks getInfo results
    assertEquals("3 5", od.getInfo());
    assertEquals("2 3", od2.getInfo());
    assertEquals("3 5", od3.getInfo());

    // tests that same dimensions give same getInfo results
    assertFalse(od.getInfo().equals(od2.getInfo()));
    assertEquals(od3.getInfo(), od.getInfo());
  }

}
