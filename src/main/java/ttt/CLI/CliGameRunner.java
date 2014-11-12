package ttt.CLI;

import ttt.Display;
import ttt.Game;

public class CliGameRunner {
  public static void main(String[] args) {
    Display display = new CliDisplay(System.out, System.in);
    Game game = new Game(display);
    game.start();
  }
}
