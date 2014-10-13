package ttt;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;

public class BoardTest {

  int defaultAvailableMoves = 9;
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void itHasNineMoves() {
    assertEquals(defaultAvailableMoves, board.numberOfAvailableMoves());
  }

  @Test
  public void itMarksAPositionOnTheGrid() {
    board.markPosition(0, "X");
    assertEquals(defaultAvailableMoves - 1, board.numberOfAvailableMoves());
  }
  }
}
