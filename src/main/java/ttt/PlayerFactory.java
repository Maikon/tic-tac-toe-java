package ttt;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
  private final Display display;

  public PlayerFactory(Display display) {
    this.display = display;
  }

  public List<HumanPlayer> buildHumanPlayers() {
    List<HumanPlayer> players = new ArrayList<>();
    players.add(new HumanPlayer(display));
    players.add(new HumanPlayer(display));
    return players;
  }
}
