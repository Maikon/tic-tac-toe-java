package ttt;

import ttt.Exceptions.InvalidMoveException;

import java.util.Collections;
import java.util.List;

public class Game {
  private final Display display;
  private final PlayerFactory factory;
  private Board board;
  private List<Player> players;

  public Game(Board board, Display display) {
    this.board = board;
    this.display = display;
    factory = new PlayerFactory(display);
  }

  public void start() {
    display.greetPlayers();
    display.showGameOptions();
    setTwoPlayers();
    while (!isOver()) {
      display.show(getBoard());
      nextPlayerMakesMove();
    }
    display.showResults(getBoard());
  }

  public void setTwoPlayers() {
    players = factory.getPlayersForChoice(display.getGameChoice());
  }

  public void nextPlayerMakesMove() {
    try {
      currentPlayerMakesMove();
      Collections.rotate(players, 1);
    } catch (InvalidMoveException e) {
      display.showInvalidMoveMessage();
    }
  }

  public List<Player> getPlayers() {
    return players;
  }

  public List<String> getBoardGrid() {
    return getBoard().getGrid();
  }

  public boolean isOver() {
    return getBoard().isOver();
  }

  private void currentPlayerMakesMove() {
    board = getPlayers().get(0).makeMove(getBoard());
  }

  private Board getBoard() {
    return board;
  }
}
