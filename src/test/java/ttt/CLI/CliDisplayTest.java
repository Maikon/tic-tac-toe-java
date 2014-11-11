package ttt.CLI;

import org.junit.Before;
import org.junit.Test;
import ttt.Board;

import java.io.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CliDisplayTest {

  private CliDisplay display;
  private OutputStream output;
  private PrintStream printStream;

  @Before
  public void setup() {
    output = new ByteArrayOutputStream();
    printStream = new PrintStream(output);
    InputStream inputStream = new ByteArrayInputStream("".getBytes());
    display = new CliDisplay(printStream, inputStream);
  }

  @Test
  public void greetsThePlayers() {
    display.greetPlayers();
    assertThat(output.toString(), containsString(display.WELCOMING_MESSAGE));
  }

  @Test
  public void prints3x3Board() {
    Board emptyBoard = new Board(3);
    display.show(emptyBoard);
    String expected = "1  |  2  |  3\n" +
                      "---|-----|---\n" +
                      "4  |  5  |  6\n" +
                      "---|-----|---\n" +
                      "7  |  8  |  9\n";
    assertThat(output.toString(), containsString(expected));
  }

  @Test
  public void prints4x4Board() {
    Board emptyBoard = new Board(4);
    display.show(emptyBoard);
    String expected = "1  |  2  |  3  |  4\n" +
                      "---|-----|-----|---\n" +
                      "5  |  6  |  7  |  8\n" +
                      "---|-----|-----|---\n" +
                      "9  |  10  |  11  |  12\n" +
                      "---|-----|-----|---\n" +
                      "13  |  14  |  15  |  16\n";
    assertThat(output.toString(), containsString(expected));
  }

  @Test
  public void asksForAMove() {
    display.askForMove();
    assertThat(output.toString(), containsString(display.MOVE_PROMPT));
  }

  @Test
  public void returnsTheInputFromUser() {
    InputStream inputStream = new ByteArrayInputStream("9\n".getBytes());
    CliDisplay display = new CliDisplay(printStream, inputStream);
    assertThat(display.getMove(), is(9));
  }

  @Test
  public void returnsInvalidMoveFromUser() {
    InputStream inputStream = new ByteArrayInputStream("y\n".getBytes());
    CliDisplay display = new CliDisplay(printStream, inputStream);
    assertThat(display.getMove(), is(display.INVALID_MOVE));
  }

  @Test
  public void printsInvalidMoveMessage() {
    display.showInvalidMoveMessage();
    assertThat(output.toString(), containsString(display.INVALID_MOVE_MESSAGE));
  }

  @Test
  public void printsTheResultForAWin() {
    Board board = new Board(asList("X", "X", "X", "O", "O", "", "", "", ""));
    display.showResults(board);
    assertThat(output.toString(), containsString("X won the game!"));
  }

  @Test
  public void printsTheResultForADraw() {
    Board board = new Board(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    display.showResults(board);
    assertThat(output.toString(), containsString(display.DRAW_MESSAGE));
  }

  @Test
  public void returnsValidGameChoiceFromUser() {
    InputStream inputStream = new ByteArrayInputStream("invalid\n2\n".getBytes());
    CliDisplay display = new CliDisplay(printStream, inputStream);
    assertThat(display.getGameChoice(), is("2"));
  }

  @Test
  public void showsInvalidChoiceMessage() {
    InputStream inputStream = new ByteArrayInputStream("invalid\n2\n".getBytes());
    CliDisplay display = new CliDisplay(printStream, inputStream);
    display.getGameChoice();
    assertThat(output.toString(), containsString(display.INVALID_GAME_CHOICE));
  }

  @Test
  public void showAllGameOptions() {
    display.showGameOptions();
    assertThat(output.toString(), containsString(display.GAME_OPTIONS));
  }
}
