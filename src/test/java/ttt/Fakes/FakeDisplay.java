package ttt.Fakes;

import ttt.Board;
import ttt.Display;

import java.util.LinkedList;
import java.util.List;

public class FakeDisplay implements Display {
  private LinkedList<Integer> listOfMoves;
  private boolean called = false;
  private boolean greeted = false;
  private boolean showed = false;

  public FakeDisplay(List<Integer> moves) {
    setMoves(moves);
  }

  @Override
  public void show(Board board) {
    this.showed = true;
  }

  @Override
  public int getMove() {
    return listOfMoves.pop();
  }

  @Override
  public void greetPlayers() {
    this.greeted = true;
  }

  @Override
  public void askForMove() {
    this.called = true;
  }

  public void setMoves(List<Integer> moves) {
    listOfMoves = new LinkedList<>(moves);
  }

  public boolean askedForMoveAgain() {
    return called;
  }

  public boolean greetedPlayer() {
    return greeted;
  }

  public boolean showedBoard() {
    return showed;
  }
}
