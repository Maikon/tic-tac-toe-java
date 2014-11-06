package ttt;

public interface Display {
  void show(Board board);

  int getMove();

  void greetPlayers();

  void askForMove();

  void showInvalidMoveMessage();

  void showResults(Board board);

  void showGameOptions();

  String getGameChoice();
}
