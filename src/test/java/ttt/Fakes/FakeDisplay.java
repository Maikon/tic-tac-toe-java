package ttt.Fakes;

import ttt.Board;
import ttt.Display;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class FakeDisplay implements Display {
  private LinkedList<Integer> listOfMoves;
  private LinkedList<String> gameChoices;
  private boolean called = false;
  private boolean greeted = false;
  private boolean showed = false;
  private boolean showedResults = false;
  private boolean gotChoice = false;
  private boolean showedOption = false;

  public FakeDisplay(List<Integer> moves) {
    this(moves, asList(""));
  }

  public FakeDisplay(List<Integer> moves, List<String> choices) {
    setMoves(moves);
    setGameChoices(choices);
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
  }

  public void setMoves(List<Integer> moves) {
    listOfMoves = new LinkedList<>(moves);
  }

  public void setGameChoices(List<String> choices) {
    gameChoices = new LinkedList<>(choices);
  }

  @Override
  public void showInvalidMoveMessage() {
    this.called = true;
  }

  @Override
  public void showResults(Board board) {
    this.showedResults = true;
  }

  @Override
  public void showGameOptions() {
    this.showedOption = true;
  }

  @Override
  public String getGameChoice() {
    this.gotChoice = true;
    return gameChoices.pop();
  }

  @Override
  public int getBoardChoice() {
    return 0;
  }

  public boolean greetedPlayer() {
    return greeted;
  }

  public boolean showedBoard() {
    return showed;
  }

  public boolean showedInvalidMoveMessage() {
    return called;
  }

  public boolean showedResults() {
    return showedResults;
  }

  public boolean gotValidChoice() {
    return gotChoice;
  }

  public boolean showedOptions() {
    return showedOption;
  }
}
