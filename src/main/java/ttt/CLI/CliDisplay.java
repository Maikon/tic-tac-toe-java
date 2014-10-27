package ttt.CLI;

import org.apache.commons.lang3.StringUtils;
import ttt.Board;
import ttt.Display;

import java.io.PrintStream;

public class CliDisplay implements Display {

  private final PrintStream output;

  public CliDisplay(PrintStream output) {
    this.output = output;
  }

  @Override
  public void greetPlayers() {
    output.println("Welcome to TicTacToe!");
  }

  @Override
  public void askForMove() {
    output.println("Please choose a move from the available ones:");
  }

  @Override
  public void show(Board board) {
    output.println(StringUtils.join(board.getRows(), "\n--|---|--\n"));
  }

  @Override
  public int getMove() {
    return 0;
  }
}
