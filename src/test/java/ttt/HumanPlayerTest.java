package ttt;

import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HumanPlayerTest {

  @Test
  public void makesMoveOnTheBoard() {
    FakeDisplay display = new FakeDisplay(asList(0));
    HumanPlayer player = new HumanPlayer(display);
    Board board = new Board();
    Board newBoard = player.makeMove(board);
    assertThat(newBoard.getValueInPosition(0), is("X"));
  }
}
