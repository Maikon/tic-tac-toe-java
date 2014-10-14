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
    List<String> newGrid = new ArrayList<String>(grid);
    newGrid.set(position, mark);
    return new Board(newGrid);
  }

  public List<Integer> listOfMoves() {
    List<Integer> moves = new LinkedList<Integer>();

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

  public List getRows() {
    List rowOne = asList(grid.get(0), grid.get(1), grid.get(2));
    List rowTwo = asList(grid.get(3), grid.get(4), grid.get(5));
    List rowThree = asList(grid.get(6), grid.get(7), grid.get(8));
    return asList(rowOne, rowTwo, rowThree);
  }
}
