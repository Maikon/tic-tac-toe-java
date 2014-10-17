package ttt;

import ttt.Exceptions.InvalidMoveException;

import java.util.*;

import static java.util.Arrays.asList;

public class Board {
  private List<String> grid;

  public Board() {
    this(Collections.nCopies(9, ""));
  }

  public Board(List<String> grid) {
    this.grid = grid;
  }

  public Board newBoardWithMove(int position, String mark) {
    List<String> grid = makeMove(position, mark);
    return new Board(grid);
  }

  private List<String> makeMove(int position, String mark) {
    List<String> newGrid = new ArrayList<>(grid);
    if (invalidMove(position)) {
      throw new InvalidMoveException();
    }
    newGrid.set(position, mark);
    return newGrid;
  }

  private boolean invalidMove(int position) {
    return !listOfMoves().contains(position);
  }
  public String lastMoveMark() {
    return currentMark().equals("X") ? "O" : "X";
  }

  public String currentMark() {
    return totalAvailableMovesIsEven() ? "O" : "X";
  }

  private boolean totalAvailableMovesIsEven() {
    return numberOfAvailableMoves() % 2 == 0;
  }

  public int numberOfAvailableMoves() {
    return listOfMoves().size();
  }

  public List<Integer> listOfMoves() {
    List<Integer> moves = new LinkedList<>();
    addAvailableMoves(moves);
    return moves;
  }

  private void addAvailableMoves(List<Integer> moves) {
    for(int i=0; i < grid.size(); i++) {
      if(grid.get(i).equals("")) {
        moves.add(i);
      }
    }
  }

  public boolean isOver() {
    return hasDraw() || hasWinner();
  }

  public boolean hasWinner() {
    for (Line combo : getCombinations()) {
      if(combo.hasWinner()) {
        return true;
      }
    }
    return false;
  }

  public boolean hasDraw() {
    return !hasWinner() && listOfMoves().isEmpty();
  }

  public List<Line> getCombinations() {
    List<Line> combos = new ArrayList<>();
    combos.addAll(getRows());
    combos.addAll(getColumns());
    combos.addAll(getDiagonals());
    return combos;
  }

  public List<Line> getRows() {
    return asList(getLine(0, 1, 2),
                  getLine(3, 4, 5),
                  getLine(6, 7, 8));
  }

  private List<Line> getColumns() {
    return asList(getLine(0, 3, 6),
                  getLine(1, 4, 7),
                  getLine(2, 5, 8));
  }

  private List<Line> getDiagonals() {
    return asList(getLine(0, 4, 8),
                  getLine(2, 4, 6));
  }

  private Line getLine(int first, int second, int third) {
    List<String> positions = asList(grid.get(first), grid.get(second), grid.get(third));
    return new Line(positions);
  }

  public List<String> getGrid() {
    return grid;
  }

  public void setGrid(List<String> grid) {
    this.grid = grid;
  }
}
