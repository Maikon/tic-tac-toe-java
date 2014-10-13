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
    return moves;
  }
}
