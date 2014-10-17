package ttt;

import java.util.Collections;
import java.util.List;

public class Game {
  private Board board;
  private List<HumanPlayer> players;

  public Game(Board board) {
    this.board = board;
  }

  public Game() {
  }

  public void getTwoHumanPlayers(Display display) {
    PlayerFactory factory = new PlayerFactory(display);
    players = factory.buildHumanPlayers();
  }

  public void nextPlayerMakesMove() {
    board = getPlayers().get(0).makeMove(board);
    Collections.rotate(players, 1);
  }

  public List<HumanPlayer> getPlayers() {
    return players;
  }

  public List<String> getBoardGrid() {
    return board.getGrid();
  }
}
