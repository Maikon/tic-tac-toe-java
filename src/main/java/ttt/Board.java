package ttt;

import ttt.Exceptions.InvalidMoveException;

import java.util.*;

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

  public int getSize() {
    return size;
  }

  public Board newBoardWithMove(int position, String mark) {
    List<String> grid = makeMove(position - 1, mark);
    return new Board(grid);
  }

  public int numberOfMovesMade() {
    return getGrid().size() - numberOfAvailableMoves() + 1;
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

  public List<Line> getRows() {
    List<Line> rows = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      rows.add(getLineRange(i * size, size - 1));
    }
    return rows;
  }

  public HashMap<Integer, String> getPositions() {
    HashMap<Integer, String> gridPairs = new HashMap<>();
    for (int i = 0; i < getGrid().size(); i++) {
      gridPairs.put(i + 1, getGrid().get(i));
    }
    return gridPairs;
  }

  private List<Line> getCombinations() {
    List<Line> combos = new ArrayList<>();
    combos.addAll(getRows());
    combos.addAll(getColumns());
    combos.addAll(getDiagonals());
    return combos;
  }

  private void addAvailableMovesPlusOne(List<Integer> moves) {
    for(int i=0; i < getGrid().size(); i++) {
      if(getGrid().get(i).equals("")) {
        moves.add(i + 1);
      }
    }
  }

  private void addAvailableMoves(List<Integer> moves) {
    for(int i=0; i < getGrid().size(); i++) {
      if(getGrid().get(i).equals("")) {
        moves.add(i);
      }
    }
  }

  private boolean totalAvailableMovesIsEven() {
    return numberOfMovesMade() % 2 == 0;
  }

  private List<String> makeMove(int position, String mark) {
    List<String> newGrid = new ArrayList<>(getGrid());
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
    List<Line> columns = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      List<String> column = new ArrayList<>();
      for (Line row : getRows()) {
        column.add(row.getElementAt(i));
      }
      columns.add(new Line(column));
    }
    return columns;
  }

  private List<Line> getDiagonals() {
    List<Line> diagonals = new ArrayList<>();
    List<String> leftDiagonal = new ArrayList<>();
    List<String> rightDiagonal = new ArrayList<>();

    int counter = size - 1;
    for (int i = 0; i < size; i++) {
      leftDiagonal.add(getRows().get(i).getElementAt(i));
      rightDiagonal.add(getRows().get(i).getElementAt(counter));
      counter -= 1;
    }

    diagonals.add(new Line(leftDiagonal));
    diagonals.add(new Line(rightDiagonal));
    return diagonals;
  }

  private Line getLineRange(int startIndex, int count) {
    List<String> positions = new ArrayList<>();
    int endIndex = startIndex + count;
    for (int position = startIndex; position <= endIndex; position++) {
      positions.add(getGrid().get(position));
    }
    return new Line(positions);
  }
}
