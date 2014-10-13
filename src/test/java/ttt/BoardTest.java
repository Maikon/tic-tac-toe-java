package ttt;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BoardTest {

  int defaultAvailableMoves = 9;

  @Test
  public void itHasNineMoves() {
    Board board = new Board();
    assertEquals(defaultAvailableMoves, board.getMoves());
  }
}
