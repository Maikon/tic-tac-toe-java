package ttt;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class LineTest {

  @Test
  public void returnsFalseIfItHasNoWinner() {
    Line line = new Line(asList("", "", ""));
    assertEquals(false, line.hasWinner());
  }

  @Test
  public void returnsTrueIfItHasWinner() {
    Line line = new Line(asList("X", "X", "X"));
    assertEquals(true, line.hasWinner());
  }
}