package ttt;

import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  @Test
  public void itSetsTwoPlayers() {
    Game game = new Game();
    FakeDisplay display = new FakeDisplay();
    game.getTwoHumanPlayers(display);
    assertThat(game.getPlayers().size(), is(2));
  }

  @Test
  public void firstPlayerMakesMove() {
    FakeDisplay display = new FakeDisplay(0);
    Board board = new Board();
    Game game = new Game(board);
    game.getTwoHumanPlayers(display);
    Board newBoard = game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "", "", "", "", "", "", "", "");
    assertThat(newBoard.getGrid(), equalTo(resultedBoard));
  }
}

