package ttt;

public class HumanPlayer implements Player {
  private Display display;

  public HumanPlayer(Display display) {
    this.display = display;
  }

  @Override
  public Board makeMove(Board board) {
    return board.newBoardWithMove(display.getMove(), board.currentMark());
  }
}
