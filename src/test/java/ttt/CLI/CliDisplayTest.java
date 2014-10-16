package ttt.CLI;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
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
}
