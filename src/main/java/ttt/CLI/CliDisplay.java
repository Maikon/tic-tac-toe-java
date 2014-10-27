package ttt.CLI;

import org.apache.commons.lang3.StringUtils;
import ttt.Board;
import ttt.Display;

import java.io.*;

public class CliDisplay implements Display {

  private final PrintStream outputStream;
  private final BufferedReader inputStream;

  public CliDisplay(PrintStream outputStream, InputStream inputStream) {
    this.outputStream = outputStream;
    this.inputStream = new BufferedReader(new InputStreamReader(inputStream));
  }

  @Override
  public void greetPlayers() {
    outputStream.println("Welcome to TicTacToe!");
  }

  @Override
  public void askForMove() {
    outputStream.println("Please choose a move from the available ones:");
  }

  @Override
  public void show(Board board) {
    outputStream.println(StringUtils.join(board.getRows(), "\n--|---|--\n"));
  }

  @Override
  public int getMove() {
    askForMove();
    String move;
    try {
      move = inputStream.readLine();
      return Integer.parseInt(move);
    } catch (IOException | NumberFormatException e) {
      return -1;
    }
  }
}
