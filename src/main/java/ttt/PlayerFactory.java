package ttt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerFactory {
  private final Display display;

  public PlayerFactory(Display display) {
    this.display = display;
  }

  public List<Player> getPlayersForChoice(String choice) {
   return getPlayerCombo(choice);
  }

  private List<Player> getPlayerCombo(String choice) {
    HashMap<String, List<Player>> combos = new HashMap<>();
    combos.put("1", createPlayers(new HumanPlayer(display), new HumanPlayer(display)));
    combos.put("2", createPlayers(new HumanPlayer(display), new ComputerPlayer()));
    combos.put("3", createPlayers(new ComputerPlayer(), new HumanPlayer(display)));
    combos.put("4", createPlayers(new ComputerPlayer(), new ComputerPlayer()));
    return combos.get(choice);
  }

  private List<Player> createPlayers(Player playerOne, Player playerTwo) {
    List<Player> players = new ArrayList<>();
    players.add(playerOne);
    players.add(playerTwo);
    return players;
  }
}
