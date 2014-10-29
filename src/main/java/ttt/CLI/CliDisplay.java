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
  final String WELCOMING_MESSAGE = "Welcome to TicTacToe!";
  final String MOVE_PROMPT = "Please choose a move from the available ones:";
  final String INVALID_MOVE_MESSAGE = "--- Invalid Move ---";
  final String DRAW_MESSAGE = "The game is a draw!";

  public CliDisplay(PrintStream outputStream, InputStream inputStream) {
    this.outputStream = outputStream;
    this.inputStream = new BufferedReader(new InputStreamReader(inputStream));
  }

  @Override
  public void greetPlayers() {
    outputStream.println(WELCOMING_MESSAGE);
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

  @Override
  public void askForMove() {
    outputStream.println(MOVE_PROMPT);
  }

  @Override
  public void showInvalidMoveMessage() {
    outputStream.println(INVALID_MOVE_MESSAGE);
  }

  @Override
  public void showResults(Board board) {
    if (board.hasWinner()) {
      outputStream.println(board.lastMoveMark() + " won the game!");
    } else {
      outputStream.println(DRAW_MESSAGE);
    }
  }

  @Override
  public void show(Board board) {
    HashMap<Integer, String> grid = board.getPositions();
    String content = "";
    int counter = 0;
    for (Map.Entry<Integer, String> entry : grid.entrySet()) {
      content = buildInitialString(content, entry);
      counter++;
      content = addSpacingWithLineBreaks(content, counter);
    }
    outputStream.print(content);
  }

  private String addSpacingWithLineBreaks(String content, int counter) {
    if(counter % 3 == 0) {
      content += "\n";
      content = addCustomLineBreaker(content, counter);
    } else {
      content += " | ";
    }
    return content;
  }

  private String addCustomLineBreaker(String content, int counter) {
    if(counter < 8) {
      content += "--|---|--\n";
    }
    return content;
  }

  private String buildInitialString(String content, Map.Entry<Integer, String> entry) {
    if (entry.getValue().equals("")){
      content += entry.getKey().toString();
    } else {
      content += entry.getValue();
    }
    return content;
  }
}
