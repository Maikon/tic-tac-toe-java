package ttt.CLI;

import ttt.Board;
import ttt.Display;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CliDisplay implements Display {

  private final PrintStream outputStream;
  private final BufferedReader inputStream;
  final int INVALID_MOVE = -1;

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
  public void showInvalidMoveMessage() {
    outputStream.println("--- Invalid Move ---");
  }

  @Override
  public void showResults(Board board) {
    if (board.hasWinner()) {
      outputStream.println(board.lastMoveMark() + " won the game!");
    } else {
      outputStream.println("The game is a draw!");
    }
  }

  @Override
  public void show(Board board) {
    HashMap<Integer, String> grid = board.getPositions();
    String content = "";
    int counter = 0;
    for (Map.Entry<Integer, String> entry : grid.entrySet()) {
      if (entry.getValue().equals("")){
        content += entry.getKey().toString();
      } else {
        content += entry.getValue();
      }
      counter++;
      if(counter % 3 == 0) {
        content += "\n";
        if(counter < 8) {
          content += "--|---|--\n";
        }
      } else {
        content += " | ";
      }
    }
    outputStream.print(content);
  }

  @Override
  public int getMove() {
    askForMove();
    String move;
    try {
      move = inputStream.readLine();
      return Integer.parseInt(move);
    } catch (IOException | NumberFormatException e) {
      return INVALID_MOVE;
    }
  }
}
