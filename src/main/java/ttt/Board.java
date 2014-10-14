package ttt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {
  private List<String> grid;

  public Board() {
    grid = new ArrayList<String>(Collections.<String>nCopies(9, null));
  }

  public int numberOfAvailableMoves() {
    return listOfMoves().size();
  }

  public void markPosition(int position, String mark) {
    grid.set(position, mark);
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
