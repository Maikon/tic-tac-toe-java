package ttt;

import java.util.List;

public class Line {
  private List<String> positions;

  public Line(List<String> positions) {
    this.positions = positions;
  }

  public boolean hasWinner() {
    return positionsAreNotEmpty() && positionsHaveSameMark();
  }

  private boolean positionsHaveSameMark() {
    return positions.stream()
                    .allMatch(position -> position.equals(positions.get(0)));
  }

  private boolean positionsAreNotEmpty() {
    return positions.stream()
                    .filter(position -> !position.isEmpty())
                    .findAny()
                    .isPresent();
  }
}
