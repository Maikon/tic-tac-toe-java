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
  final String DIVIDER = "*******************************";
  final String BOARD_CHOICE_MESSAGE = "Please choose 1 or 2 for board size: 1. 3x3 2. 4x4";
  final String INVALID_BOARD_CHOICE = "Please choose 1 for 3x3 or 2 for 4x4:";
  final String WELCOMING_MESSAGE = "Welcome to TicTacToe!";
  final String MOVE_PROMPT = "Please choose a move from the available ones:";
  final String INVALID_MOVE_MESSAGE = "--- Invalid Move ---";
  final String INVALID_GAME_CHOICE = "Please choose a valid game type: ";
  final String DRAW_MESSAGE = "The game is a draw!";
  final String GAME_OPTIONS = "Please choose a game type from 1-4\n" +
                              " " + validGameOptions().get("1") +
                              " " + validGameOptions().get("2") +
                              " " + validGameOptions().get("3") +
                              " " + validGameOptions().get("4");

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

  public void askForBoardChoice() {
    outputStream.println(BOARD_CHOICE_MESSAGE);
  }

  @Override
  public void showGameOptions() {
    outputStream.println(GAME_OPTIONS);
  }

  @Override
  public String getGameChoice() {
    String move;
    try {
      move = inputStream.readLine();
      while (!validGameOptions().containsKey(move)) {
        outputStream.println(INVALID_GAME_CHOICE);
        move = inputStream.readLine();
      }
      return move;
    } catch (IOException e) {
      return "invalid";
    }
  }

  @Override
  public int getBoardChoice() {
    String choice;
    askForBoardChoice();
    try {
      choice = inputStream.readLine();
      while(!validBoardChoices().containsKey(choice)) {
        outputStream.println(INVALID_BOARD_CHOICE);
        choice = inputStream.readLine();
      }
    } catch (IOException e) {
      choice = "";
    }
    return validBoardChoices().get(choice);
  }

  @Override
  public void show(Board board) {
    HashMap<Integer, String> grid = board.getPositions();
    String content = "";
    int counter = 0;
    for (Map.Entry<Integer, String> entry : grid.entrySet()) {
      content = buildInitialString(content, entry);
      counter++;
      content = addSpacingWithLineBreaks(board, content, counter);
    }
    outputStream.println(DIVIDER);
    outputStream.print(content);
  }

  private String addSpacingWithLineBreaks(Board board, String content, int counter) {
    if(counter % board.getSize() == 0) {
      content += "\n";
      content = addCustomLineBreaker(board, content, counter);
    } else {
      content += "  |  ";
    }
    return content;
  }

  private String addCustomLineBreaker(Board board, String content, int counter) {
    if (board.getGrid().size() == 16) {
      content += "---|-----|-----|---\n";
    } else {
      content += "---|-----|---\n";
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

  private Map<String, String> validGameOptions() {
    Map<String, String> combinations = new HashMap<>();
    combinations.put("1", "1. Human Vs Human");
    combinations.put("2", "2. Human Vs Computer");
    combinations.put("3", "3. Computer Vs Human");
    combinations.put("4", "4. Computer Vs Computer");
    return combinations;
  }

  private Map<String, Integer> validBoardChoices() {
    Map<String, Integer> options = new HashMap<>();
    options.put("1", 3);
    options.put("2", 4);
    return options;
  }
}
