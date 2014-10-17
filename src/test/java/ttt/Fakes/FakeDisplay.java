package ttt.Fakes;

import ttt.Board;
import ttt.Display;

import java.util.LinkedList;
import java.util.List;

public class FakeDisplay implements Display {
  private LinkedList<Integer> listOfMoves;

  public FakeDisplay(List<Integer> moves) {
    listOfMoves = new LinkedList<Integer>();
    for (Integer move : moves) {
      listOfMoves.add(move);
    }
  }

  @Override
  public void show(Board board) {
  }

  @Override
  public int getMove() {
    return listOfMoves.pop();
  }

  @Override
  public void greetPlayers() {
  }
}
