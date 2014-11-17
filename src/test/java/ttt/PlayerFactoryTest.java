package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerFactoryTest {
  private PlayerFactory factory;

  @Before
  public void setup() {
    factory =  new PlayerFactory(new FakeDisplay(asList(0)));
  }

  @Test
  public void humanVsHumanChoice() {
    List<Player> players = factory.getPlayersForChoice(PlayerFactory.HVH);
    assertThat(players.get(0), instanceOf(HumanPlayer.class));
    assertThat(players.get(1), instanceOf(HumanPlayer.class));
  }

  @Test
  public void humanVsComputerChoice() {
    List<Player> players = factory.getPlayersForChoice(PlayerFactory.HVC);
    assertThat(players.get(0), instanceOf(HumanPlayer.class));
    assertThat(players.get(1), instanceOf(ComputerPlayer.class));
  }

  @Test
  public void computerVsHumanChoice() {
    List<Player> players = factory.getPlayersForChoice(PlayerFactory.CVH);
    assertThat(players.get(0), instanceOf(ComputerPlayer.class));
    assertThat(players.get(1), instanceOf(HumanPlayer.class));
  }

  @Test
  public void computerVsComputerChoice() {
    List<Player> players = factory.getPlayersForChoice(PlayerFactory.CVC);
    assertThat(players.get(0), instanceOf(ComputerPlayer.class));
    assertThat(players.get(1), instanceOf(ComputerPlayer.class));
  }

  @Test
  public void returnsAllGameChoicesDescriptions() {
    assertThat(PlayerFactory.allGameChoices().get(PlayerFactory.HVH), is(PlayerFactory.HUMAN_VS_HUMAN));
    assertThat(PlayerFactory.allGameChoices().get(PlayerFactory.HVC), is(PlayerFactory.HUMAN_VS_COMPUTER));
    assertThat(PlayerFactory.allGameChoices().get(PlayerFactory.CVH), is(PlayerFactory.COMPUTER_VS_HUMAN));
    assertThat(PlayerFactory.allGameChoices().get(PlayerFactory.CVC), is(PlayerFactory.COMPUTER_VS_COMPUTER));
  }
}