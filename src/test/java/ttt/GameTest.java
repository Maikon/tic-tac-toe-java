package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class GameTest {

  private static final int INVALID_MOVE = 10;
  private static final String HUMAN_VS_HUMAN = "1";
  private Game game;
  private FakeDisplay display;

  @Before
  public void setup() {
    List<Integer> moves = asList(0, 1);
    List<String> choices = asList(HUMAN_VS_HUMAN);
    display = new FakeDisplay(moves, choices);
    game = new Game(display);
    game.setTwoPlayers();
  }

  @Test
  public void bothPlayersMakeMove() {
    game.nextPlayerMakesMove();
    game.nextPlayerMakesMove();

    assertThat(game.valueInPosition(0), is("X"));
    assertThat(game.valueInPosition(1), is("O"));
  }

  @Test
  public void raisesExceptionGivenInvalidMove() {
    display.setMoves(asList(INVALID_MOVE));
    game.nextPlayerMakesMove();

    assertThat(display.showedInvalidMoveMessage(), is(true));
  }


  @Test
  public void samePlayerGoesAgainIfMoveInvalid() {
    display.setMoves(asList(INVALID_MOVE));
    game.nextPlayerMakesMove();

    assertThat(game.markThatGoesNext(), is("X"));
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
    display.setGameChoices(asList(HUMAN_VS_HUMAN));
    game.setTwoPlayers();

    assertThat(display.gotValidChoice(), is(true));
  }

  @Test
  public void playsTheGameUntilOver() {
    FakeDisplay display = createDisplay(winningMoves(), asList(HUMAN_VS_HUMAN));
    Game game = new Game(display);
    game.start();

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void getsBoardSizeFromDisplay() {
    FakeDisplay display = createDisplay(winningMoves(), asList(HUMAN_VS_HUMAN));
    new Game(display);

    assertTrue(display.gotBoardChoice());
  }

  private FakeDisplay createDisplay(List<Integer> moves, List<String> choices) {
    return new FakeDisplay(moves, choices);
  }

  private void setMovesAndGameChoice() {
    display.setMoves(winningMoves());
    display.setGameChoices(asList(HUMAN_VS_HUMAN));
  }

  private List<Integer> winningMoves() {
    return asList(0, 5, 1, 4, 2);
  }
}

