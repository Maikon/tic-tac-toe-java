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
  private FakeDisplay display;

  @Before
  public void setup() {
    List<Integer> moves = asList(1, 2, 9);
    display = new FakeDisplay(moves);
    Board board = new Board();
    game = new Game(board, display);
    game.setTwoPlayers();
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
  public void raisesExceptionGivenInvalidMove() {
    display.setMoves(asList(10));
    game.nextPlayerMakesMove();
    assertThat(display.showedInvalidMoveMessage(), is(true));
  }


  @Test
  public void samePlayerGoesAgainIfMoveInvalid() {
    display.setMoves(asList(10, 1));
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "", "", "", "", "", "", "", "");
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
    Game game = new Game(drawBoard, display);
    assertThat(game.isOver(), is(true));
  }

  @Test
  public void greetsThePlayerBeforeStarting(){
    display.setMoves(asList(1, 6, 2, 5, 3));
    game.start();
    assertThat(display.greetedPlayer(), is(true));
  }

  @Test
  public void showsTheBoard() {
    display.setMoves(asList(1, 6, 2, 5, 3));
    game.start();
    assertThat(display.showedBoard(), is(true));
  }

  @Test
  public void showResults() {
    display.setMoves(asList(1, 6, 2, 5, 3));
    game.start();
    assertThat(display.showedResults(), is(true));
  }

  @Test
  public void playsTheGameUntilOver() {
    Board board = new Board();
    List<Integer> moves = asList(1, 6, 2, 5, 3);
    Display display = new FakeDisplay(moves);
    Game game = new Game(board, display);
    game.start();
    assertThat(game.isOver(), is(true));
  }
}

