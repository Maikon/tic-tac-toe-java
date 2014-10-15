package ttt;

public class PlayerFactory {
  private HumanPlayer playerOne;
  private HumanPlayer playerTwo;

  public PlayerFactory() {
    playerOne = new HumanPlayer();
    playerTwo = new HumanPlayer();
  }

  public HumanPlayer getPlayerOne() {
    return playerOne;
  }

  public HumanPlayer getPlayerTwo() {
    return playerTwo;
  }
}
