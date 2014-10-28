package ttt;

import ttt.Exceptions.InvalidMoveException;

import java.util.Collections;
import java.util.List;

public class Game {
  private final Display display;
  private Board board;
  private List<HumanPlayer> players;

  public Game(Board board, Display display) {
    this.board = board;
    this.display = display;
  }

  public void setTwoPlayers() {
    PlayerFactory factory = new PlayerFactory(display);
    players = factory.buildHumanPlayers();
  }

  public void nextPlayerMakesMove() {
    try {
      board = getPlayers().get(0).makeMove(board);
      Collections.rotate(players, 1);
    } catch (InvalidMoveException e) {
      display.askForMove();
    }
  }

  public List<HumanPlayer> getPlayers() {
    return players;
  }

  public List<String> getBoardGrid() {
    return board.getGrid();
  }

  public boolean isOver() {
    return board.isOver();
  }

  public void start() {
    display.greetPlayers();
    setTwoPlayers();
    while (!isOver()) {
      display.show(getBoard());
      nextPlayerMakesMove();
    }
  }

  private Board getBoard() {
    return board;
  }
}
