package ttt.CLI;

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
  public int getMove() {
    return 0;
  }
}
