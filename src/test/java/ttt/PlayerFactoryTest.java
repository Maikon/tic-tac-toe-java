package ttt;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayerFactoryTest {

  @Test
  public void itCreatesTwoHumanPlayers() {
    PlayerFactory factory =  new PlayerFactory();
    assertTrue(HumanPlayer.class == factory.getPlayerOne().getClass());
    assertTrue(HumanPlayer.class == factory.getPlayerTwo().getClass());
  }
}