package ttt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFactory {
  public static final String HUMAN_VS_HUMAN = "1. Human Vs Human";
  public static final String HUMAN_VS_COMPUTER = "2. Human Vs Computer";
  public static final String COMPUTER_VS_HUMAN = "3. Computer Vs Human";
  public static final String COMPUTER_VS_COMPUTER = "4. Computer Vs Computer";
  public static final String HVH = "1";
  public static final String HVC = "2";
  public static final String CVH = "3";
  public static final String CVC = "4";
  private final Display display;
  private final Map<String, List<Player>> combos;

  public PlayerFactory(Display display) {
    this.display = display;
    this.combos = new HashMap<>();
  }

  public static Map<String, String> allGameChoices() {
    Map<String, String> choices = new HashMap<>();
    choices.put(HVH, HUMAN_VS_HUMAN);
    choices.put(HVC, HUMAN_VS_COMPUTER);
    choices.put(CVH, COMPUTER_VS_HUMAN);
    choices.put(CVC, COMPUTER_VS_COMPUTER);
    return choices;
  }

  public List<Player> getPlayersForChoice(String choice) {
   return getPlayerCombo().get(choice);
  }

  private Map<String, List<Player>> getPlayerCombo() {
    combos.put(HVH, createPlayers(new HumanPlayer(display), new HumanPlayer(display)));
    combos.put(HVC, createPlayers(new HumanPlayer(display), new ComputerPlayer()));
    combos.put(CVH, createPlayers(new ComputerPlayer(), new HumanPlayer(display)));
    combos.put(CVC, createPlayers(new ComputerPlayer(), new ComputerPlayer()));
    return combos;
  }

  private List<Player> createPlayers(Player playerOne, Player playerTwo) {
    List<Player> players = new ArrayList<>();
    players.add(playerOne);
    players.add(playerTwo);
    return players;
  }
}
