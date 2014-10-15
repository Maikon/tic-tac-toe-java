package ttt;

import java.util.List;

public class Line {
  private String first;
  private String second;
  private String third;

  public Line(List<String> positions) {
    first = positions.get(0);
    second = positions.get(1);
    third = positions.get(2);
  }

  public boolean hasWinner() {
    if(first.isEmpty() || second.isEmpty() || third.isEmpty()) {
      return false;
    }
    return (first.equals(second) && second.equals(third));
  }
}
