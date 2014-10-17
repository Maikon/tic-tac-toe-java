package ttt;

import org.junit.Test;
import ttt.Fakes.FakeDisplay;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  @Test
  public void itSetsTwoPlayers() {
    Game game = new Game();
    FakeDisplay display = new FakeDisplay();
    game.getTwoHumanPlayers(display);
    assertThat(game.getPlayers().size(), is(2));
  }
}

