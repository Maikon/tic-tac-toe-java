package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  private Game game;
  private Board board;
  private FakeDisplay display;

  @Before
  public void setup() {
    display = new FakeDisplay(0);
    board = new Board();
    game = new Game(board);
  }

  @Test
  public void itSetsTwoPlayers() {
    game.getTwoHumanPlayers(display);
    assertThat(game.getPlayers().size(), is(2));
  }

  @Test
  public void firstPlayerMakesMove() {
    game.getTwoHumanPlayers(display);
    Board newBoard = game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "", "", "", "", "", "", "", "");
    assertThat(newBoard.getGrid(), equalTo(resultedBoard));
  }
}

