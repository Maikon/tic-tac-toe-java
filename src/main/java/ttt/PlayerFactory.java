package ttt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFactory {
  private final Display display;
  private final Map<String, List<Player>> combos;

  public PlayerFactory(Display display) {
    this.display = display;
    combos = new HashMap<>();
  }

  public List<Player> getPlayersForChoice(String choice) {
   return getPlayerCombo().get(choice);
  }

  private Map<String, List<Player>> getPlayerCombo() {
    combos.put("1", createPlayers(new HumanPlayer(display), new HumanPlayer(display)));
    combos.put("2", createPlayers(new HumanPlayer(display), new ComputerPlayer()));
    combos.put("3", createPlayers(new ComputerPlayer(), new HumanPlayer(display)));
    combos.put("4", createPlayers(new ComputerPlayer(), new ComputerPlayer()));
    return combos;
  }

  private List<Player> createPlayers(Player playerOne, Player playerTwo) {
    List<Player> players = new ArrayList<>();
    players.add(playerOne);
    players.add(playerTwo);
    return players;
  }
}
