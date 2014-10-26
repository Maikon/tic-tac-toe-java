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
    board = new Board();
    game = new Game(board);
    game.getTwoHumanPlayers(display);
  }

  @Test
  public void itSetsTwoPlayers() {
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
  public void returnFalseIfGameIsNotOver() {
    assertThat(game.isOver(), is(false));
  }

  @Test
  public void returnTrueIfGameIsOver() {
    game.setBoardGrid(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    assertThat(game.isOver(), is(true));
  }
}

