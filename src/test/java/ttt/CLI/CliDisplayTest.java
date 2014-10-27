package ttt.CLI;

import org.junit.Before;
import org.junit.Test;
import ttt.Board;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CliDisplayTest {

  private CliDisplay display;
  private OutputStream output;

  @Before
  public void setup() {
    output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    display = new CliDisplay(printStream);
  }

  @Test
  public void greetsThePlayers() {
    display.greetPlayers();
    assertThat(output.toString(), containsString("Welcome to TicTacToe!"));
  }

  @Test
  public void printsTheBoard() {
    Board board = new Board(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    display.show(board);
    String expected = "X | O | X\n" +
                      "--|---|--\n" +
                      "X | O | X\n" +
                      "--|---|--\n" +
                      "O | X | O\n";
    assertThat(output.toString(), is(expected));
  }

  @Test
  public void asksForAMove() {
    display.askForMove();
    assertThat(output.toString(), containsString("Please choose a move from the available ones:"));
  }
}
