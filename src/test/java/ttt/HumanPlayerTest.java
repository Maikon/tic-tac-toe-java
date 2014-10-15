package ttt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

  @Test
  public void itMakesMoveOnTheBoard() {
    HumanPlayer player = new HumanPlayer();
    Board board = new Board();
    Board newBoard = player.makeMove(board, 1);
    assertEquals(8, newBoard.numberOfAvailableMoves());
  }
}
