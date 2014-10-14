package ttt;

import java.util.*;

import static java.util.Arrays.asList;

public class Board {
  private final List<String> grid;

  public Board() {
    this(Collections.<String>nCopies(9, null));
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
      if(grid.get(i) == null) {
        moves.add(i);
      }
    }
  }

  public boolean hasWinner() {
    List<List<String>> combos = getCombinations();
    for (List<String> combo : combos) {
      if(hasWinner(combo)) {
        return true;
      }
    }
    return false;
  }

  private boolean hasWinner(List<String> combo) {
    String mark = combo.get(0);
    if (mark == null) {
      return false;
    }
    return combo.stream().allMatch(m -> m != null && m.equals(mark));
  }

  public List<List<String>> getCombinations() {
    List<List<String>> combos = new ArrayList<>();
    combos.addAll(getRows());
    combos.addAll(getColumns());
    combos.addAll(getDiagonals());
    return combos;
  }

  private List<List<String>> getRows() {
    return asList(getLine(0, 1, 2),
                  getLine(3, 4, 5),
                  getLine(6, 7, 8));
  }

  private List<List<String>> getColumns() {
    return asList(getLine(0, 3, 6),
                  getLine(1, 4, 7),
                  getLine(2, 5, 8));
  }

  private List<List<String>> getDiagonals() {
    return asList(getLine(0, 4, 8),
                  getLine(2, 4, 6));
  }

  private List<String> getLine(int first, int second, int third) {
    return asList(grid.get(first), grid.get(second), grid.get(third));
  }
}
