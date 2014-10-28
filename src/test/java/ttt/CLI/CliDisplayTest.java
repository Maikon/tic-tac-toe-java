package ttt.CLI;

import org.junit.Before;
import org.junit.Test;
import ttt.Board;

import java.io.*;

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
    assertThat(output.toString(), containsString("Welcome to TicTacToe!"));
  }

  @Test
  public void printsTheBoard() {
    Board emptyBoard = new Board();
    display.show(emptyBoard);
    String expected = "1 | 2 | 3\n" +
                      "--|---|--\n" +
                      "4 | 5 | 6\n" +
                      "--|---|--\n" +
                      "7 | 8 | 9\n";
    assertThat(output.toString(), is(expected));
  }

  @Test
  public void asksForAMove() {
    display.askForMove();
    assertThat(output.toString(), containsString("Please choose a move from the available ones:"));
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
}
