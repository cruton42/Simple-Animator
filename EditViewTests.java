import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.Motion;
import cs3500.animator.view.EditInterface;
import cs3500.animator.view.EditView;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Tests for the EditView class.
 */
public class EditViewTests {
  EditInterface ev;

  /**
   * Sets the initial conditions for the tests.
   */
  private void initConditions() {
    ev = new EditView();
  }

  @Test
  public void testConstructor() {
    initConditions();

    assertEquals(1, ev.getSpeed());

    assertNull(ev.getInputFrame());
    assertNull(ev.getInputShape());
    assertNull(ev.getEditColor());
    assertNull(ev.getEditPos());
    assertNull(ev.getEditDimensions());

    assertTrue(ev.isLooping());
    assertFalse(ev.isRestarted());
    assertFalse(ev.isPaused());

    assertFalse(ev.getShapeAdded());
    assertFalse(ev.getShapeRemoved());
    assertFalse(ev.getFrameAdded());
    assertFalse(ev.getFrameEdited());
    assertFalse(ev.getFrameRemoved());
  }

  @Test
  public void testGetSetSpeed() {
    initConditions();
    assertEquals(1, ev.getSpeed());
    ev.setSpeed(100);
    assertEquals(100, ev.getSpeed());
  }

  @Test
  public void testIsRestarted() {
    initConditions();

    assertFalse(ev.isRestarted());

    ev.restartRestart();
    assertFalse(ev.isRestarted());
  }

  @Test
  public void isLooping() {
    initConditions();

    assertTrue(ev.isLooping());
  }

  @Test
  public void resetInputBooleans() {
    initConditions();
    ev.resetInputBooleans();

    assertNull(ev.getInputFrame());
    assertNull(ev.getInputShape());
    assertNull(ev.getEditColor());
    assertNull(ev.getEditPos());
    assertNull(ev.getEditDimensions());

    assertFalse(ev.getShapeAdded());
    assertFalse(ev.getShapeRemoved());
    assertFalse(ev.getFrameAdded());
    assertFalse(ev.getFrameEdited());
    assertFalse(ev.getFrameRemoved());
  }

  @Test
  public void testGetInputShape() {
    initConditions();

    assertNull(ev.getInputShape());
  }

  @Test
  public void testGetInputFrame() {
    initConditions();

    assertNull(ev.getInputFrame());
  }

  @Test
  public void testGetEditColor() {
    initConditions();

    assertNull(ev.getEditColor());
  }

  @Test
  public void testGetEditPos() {
    initConditions();

    assertNull(ev.getEditColor());
  }

  @Test
  public void testGetEditDim() {
    initConditions();

    assertNull(ev.getEditDimensions());
  }

  @Test
  public void testGetShapeAdded() {
    initConditions();

    assertFalse(ev.getShapeAdded());
  }

  @Test
  public void testGetShapeRemoved() {
    initConditions();

    assertFalse(ev.getShapeRemoved());
  }


  @Test
  public void testGetFrameAdded() {
    initConditions();

    assertFalse(ev.getFrameAdded());
  }


  @Test
  public void testGetFrameRemoved() {
    initConditions();

    assertFalse(ev.getFrameRemoved());
  }

  @Test
  public void testGetFrameEdited() {
    initConditions();

    assertFalse(ev.getFrameEdited());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetMotions() {
    initConditions();
    ev.setMotions(new ArrayList<Motion>());
  }

}
