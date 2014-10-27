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

  @Before
  public void setup() {
    List<Integer> moves = asList(0, 1, 8);
    Display display = new FakeDisplay(moves);
    Board board = new Board();
    game = new Game(board);
    game.getTwoHumanPlayers(display);
  }

  @Test
  public void getsTwoPlayers() {
    assertThat(game.getPlayers().size(), is(2));
  }

  @Test
  public void bothPlayersMakeMove() {
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "O", "", "", "", "", "", "", "X");
    List<String> boardGrid = game.getBoardGrid();
    assertThat(boardGrid, equalTo(resultedBoard));
  }

  @Test
  public void whenGameIsNotOver() {
    assertThat(game.isOver(), is(false));
  }

  @Test
  public void whenGameIsOver() {
    Board drawBoard = new Board(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    Game game = new Game(drawBoard);
    assertThat(game.isOver(), is(true));
  }
}

