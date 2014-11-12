package ttt;

public interface Display {
  void show(Board board);

  int getMove();

  void showInvalidMoveMessage();

  void showResults(Board board);

  String getGameChoice();

  int getBoardChoice();
}
