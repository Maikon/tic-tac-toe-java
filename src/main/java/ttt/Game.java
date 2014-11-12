package ttt;

import ttt.Exceptions.InvalidMoveException;

import java.util.Collections;
import java.util.List;

public class Game {
  private final Display display;
  private final PlayerFactory factory;
  private Board board;
  private List<Player> players;

  Game(Board board, Display display) {
    this.board = board;
    this.display = display;
    factory = new PlayerFactory(display);
  }

  public Game(Display display) {
    this.display = display;
    this.board = new Board(display.getBoardChoice());
    factory = new PlayerFactory(display);
  }

  public void start() {
    setTwoPlayers();
    while (!isOver()) {
      playGame();
    }
    showResults();
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

  public boolean isOver() {
    return getBoard().isOver();
  }

  List<String> getBoardGrid() {
    return getBoard().getGrid();
  }

  private void playGame() {
    display.show(getBoard());
    nextPlayerMakesMove();
  }

  private void showResults() {
    display.show(getBoard());
    display.showResults(getBoard());
  }

  private void currentPlayerMakesMove() {
    board = getPlayers().get(0).makeMove(getBoard());
  }

  private Board getBoard() {
    return board;
  }
}
