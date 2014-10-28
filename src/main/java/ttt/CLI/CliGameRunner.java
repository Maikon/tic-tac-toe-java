package ttt.CLI;

import ttt.Board;
import ttt.Display;
import ttt.Game;

public class CliGameRunner {
  public static void main(String[] args) {
    Board board = new Board();
    Display display = new CliDisplay(System.out, System.in);
    Game game = new Game(board, display);
    game.start();
  }
}
