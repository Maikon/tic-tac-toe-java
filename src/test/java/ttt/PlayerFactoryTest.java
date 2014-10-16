package ttt;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerFactoryTest {

  @Test
  public void itCreatesTwoHumanPlayers() {
    PlayerFactory factory =  new PlayerFactory();
    assertThat(factory.getPlayerOne(), instanceOf(HumanPlayer.class));
    assertThat(factory.getPlayerTwo(), instanceOf(HumanPlayer.class));
  }
}