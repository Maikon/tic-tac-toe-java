package ttt;

import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerFactoryTest {

  @Test
  public void createsTwoHumanPlayers() {
    PlayerFactory factory =  new PlayerFactory(new FakeDisplay(asList(0)));
    List<HumanPlayer> players = factory.buildHumanPlayers();
    assertThat(players.get(0), instanceOf(HumanPlayer.class));
    assertThat(players.get(1), instanceOf(HumanPlayer.class));
  }
}