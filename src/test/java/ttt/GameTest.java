package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class GameTest {

  private Game game;
  private FakeDisplay display;

  @Before
  public void setup() {
    List<Integer> moves = asList(1, 2, 9);
    List<String> choices = asList("1");
    display = new FakeDisplay(moves, choices);
    Board board = new Board();
    game = new Game(board, display);
    game.setTwoPlayers();
  }

  @Test
  public void bothPlayersMakeMove() {
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();
    List<String> resultedBoard = asList("X", "O", "",
                                        "" , "" , "",
                                        "" , "" , "X");

    assertThat(game.getBoardGrid(), equalTo(resultedBoard));
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
    List<String> resultedBoard = asList("X", "", "",
                                        "" , "", "",
                                        "" , "", "");

    assertThat(game.getBoardGrid(), equalTo(resultedBoard));
  }

  @Test
  public void whenGameIsNotOver() {
    assertThat(game.isOver(), is(false));
  }

  @Test
  public void whenGameIsOver() {
    Board drawBoard = new Board(asList("X", "O", "X",
                                       "X", "O", "X",
                                       "O", "X", "O"));
    Game game = new Game(drawBoard, display);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void showsTheBoard() {
    setMovesAndGameChoice();
    game.start();

    assertThat(display.showedBoard(), is(true));
  }

  @Test
  public void showResults() {
    setMovesAndGameChoice();
    game.start();

    assertThat(display.showedResults(), is(true));
  }

  @Test
  public void getValidGameChoiceFromDisplay() {
    display.setGameChoices(asList("1"));
    game.setTwoPlayers();

    assertThat(display.gotValidChoice(), is(true));
  }

  @Test
  public void playsTheGameUntilOver() {
    Board board = new Board();
    FakeDisplay display = createDisplay(winningMoves(), asList("1"));
    Game game = new Game(board, display);
    game.start();

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void getsBoardSizeFromDisplay() {
    FakeDisplay display = createDisplay(winningMoves(), asList("1"));
    new Game(display);

    assertTrue(display.gotBoardChoice());
  }

  private FakeDisplay createDisplay(List<Integer> moves, List<String> choices) {
    return new FakeDisplay(moves, choices);
  }

  private void setMovesAndGameChoice() {
    display.setMoves(winningMoves());
    display.setGameChoices(asList("1"));
  }

  private List<Integer> winningMoves() {
    return asList(1, 6, 2, 5, 3);
  }
}

