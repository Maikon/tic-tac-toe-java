package ttt;

import java.util.List;

public class Game {
  private Board board;
  private List<HumanPlayer> players;

  public Game() {
  }

  public void getTwoHumanPlayers(Display display) {
    PlayerFactory factory = new PlayerFactory(display);
    players = factory.buildHumanPlayers();
  }
}
