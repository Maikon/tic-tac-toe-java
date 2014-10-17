package ttt.Fakes;

import ttt.Board;
import ttt.Display;

public class FakeDisplay implements Display {
  private final int move;

  public FakeDisplay() {
    this(0);
  }
  public FakeDisplay(int move) {
    this.move = move;
  }

  @Override
  public void show(Board board) {
  }

  @Override
  public int getMove() {
    return move;
  }

  @Override
  public void greetPlayers() {
  }
}
