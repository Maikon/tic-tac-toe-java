package ttt.CLI;

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

  @Test
  public void itGreetsThePlayers() {
    OutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    CliDisplay display = new CliDisplay(printStream);
    display.greetPlayers();
    assertThat(output.toString(), containsString("Welcome to TicTacToe!"));
  }

  @Test
  public void itPrintsTheBoard() {
    OutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    CliDisplay display = new CliDisplay(printStream);
    Board board = new Board(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    display.show(board);
    String expected = "X | O | X" +
                    "\n--|---|--\n" +
                      "X | O | X" +
                    "\n--|---|--\n" +
                      "O | X | O" +
                              "\n";
    assertThat(output.toString(), is(expected));
  }
}
