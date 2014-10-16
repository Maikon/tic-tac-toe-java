package ttt.Fakes;

import ttt.Display;

public class FakeDisplay implements Display {
  private final int move;

  public FakeDisplay(int move) {
    this.move = move;
  }

  @Override
  public int getMove() {
    return move;
  }
}
