package ttt;

import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HumanPlayerTest {

  @Test
  public void itMakesMoveOnTheBoard() {
    FakeDisplay display = new FakeDisplay(1);
    HumanPlayer player = new HumanPlayer(display);
    Board board = new Board();
    Board newBoard = player.makeMove(board);
    assertThat(newBoard.numberOfAvailableMoves(), equalTo(8));
  }
}
