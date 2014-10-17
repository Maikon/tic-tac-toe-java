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
    List<Integer> moves = asList(0, 1, 8);
    display = new FakeDisplay(moves);
    // display = new FakeDisplay(0);
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
    game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "", "", "", "", "", "", "", "");
    assertThat(game.getBoardGrid(), equalTo(resultedBoard));
  }

  @Test
  public void bothPlayersMakeMove() {
    game.getTwoHumanPlayers(display);
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "O", "", "", "", "", "", "", "X");
    List<String> boardGrid = game.getBoardGrid();
    assertThat(boardGrid, equalTo(resultedBoard));
  }
}

