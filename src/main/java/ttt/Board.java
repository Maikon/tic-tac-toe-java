package ttt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
}
