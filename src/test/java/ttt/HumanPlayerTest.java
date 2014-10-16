package ttt;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HumanPlayerTest {

  @Test
  public void itMakesMoveOnTheBoard() {
    HumanPlayer player = new HumanPlayer();
    Board board = new Board();
    Board newBoard = player.makeMove(board, 1);
    assertThat(newBoard.numberOfAvailableMoves(), equalTo(8));
  }
}
