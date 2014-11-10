package ttt;

import ttt.Exceptions.InvalidMoveException;

import java.util.*;

import static java.util.Arrays.asList;

public class Board {
  private int size;
  private List<String> grid;

  public Board() {
    this(Collections.nCopies(9, ""));
  }

  public Board(int size) {
    this(Collections.nCopies(size * size, ""));
    this.size = size;
  }

  public Board(List<String> grid) {
    this.grid = grid;
    this.size = (int) Math.sqrt(grid.size());
  }

  public List<String> getGrid() {
    return grid;
  }

  public Board newBoardWithMove(int position, String mark) {
    List<String> grid = makeMove(position - 1, mark);
    return new Board(grid);
  }

  public String lastMoveMark() {
    return currentMark().equals("X") ? "O" : "X";
  }

  public String currentMark() {
    return totalAvailableMovesIsEven() ? "O" : "X";
  }

  public int numberOfAvailableMoves() {
    return listOfMoves().size();
  }

  public List<Integer> listOfMoves() {
    List<Integer> moves = new LinkedList<>();
    addAvailableMoves(moves);
    return moves;
  }

  public List<Integer> listOfMovesIndexOne() {
    List<Integer> moves = new LinkedList<>();
    addAvailableMovesPlusOne(moves);
    return moves;
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
    List<Line> rows = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      rows.add(getLineRange(i * size, size - 1));
    }
    return rows;
  }

  public HashMap<Integer, String> getPositions() {
    HashMap<Integer, String> gridPairs = new HashMap<>();
    for (int i = 0; i < 9; i++) {
      gridPairs.put(i + 1, grid.get(i));
    }
    return gridPairs;
  }

  private void addAvailableMovesPlusOne(List<Integer> moves) {
    for(int i=0; i < grid.size(); i++) {
      if(grid.get(i).equals("")) {
        moves.add(i + 1);
      }
    }
  }

  private void addAvailableMoves(List<Integer> moves) {
    for(int i=0; i < grid.size(); i++) {
      if(grid.get(i).equals("")) {
        moves.add(i);
      }
    }
  }

  private boolean totalAvailableMovesIsEven() {
    return numberOfAvailableMoves() % 2 == 0;
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

  private Line getLineRange(int startIndex, int count) {
    List<String> positions = new ArrayList<>();
    int endIndex = startIndex + count;
    for (int position = startIndex; position <= endIndex; position++) {
      positions.add(grid.get(position));
    }
    return new Line(positions);
  }
}
