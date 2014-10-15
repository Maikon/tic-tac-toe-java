package ttt;

import java.util.*;

import static java.util.Arrays.asList;

public class Board {
  private final List<String> grid;

  public Board() {
    this(Collections.nCopies(9, ""));
  }

  public Board(List<String> grid) {
    this.grid = grid;
  }

  public int numberOfAvailableMoves() {
    return listOfMoves().size();
  }

  public Board newBoardWithMove(int position, String mark) {
    List<String> newGrid = new ArrayList<>(grid);
    newGrid.set(position, mark);
    return new Board(newGrid);
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

  public boolean hasWinner() {
    for (Line combo : getCombinations()) {
      if(combo.hasWinner()) {
        return true;
      }
    }
    return false;
  }

  public boolean hasDraw() {
    for (Line combo : getCombinations()) {
      if(combo.hasDraw()) {
        return true;
      }
    }
    return false;
  }

  public boolean isOver() {
    return hasDraw() || hasWinner();
  }

  public List<Line> getCombinations() {
    List<Line> combos = new ArrayList<>();
    combos.addAll(getRows());
    combos.addAll(getColumns());
    combos.addAll(getDiagonals());
    return combos;
  }

  private List<Line> getRows() {
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
}
