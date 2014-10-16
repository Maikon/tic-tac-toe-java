package ttt;

public class HumanPlayer {
  private Display display;

  public HumanPlayer(Display display) {
    this.display = display;
  }

  public Board makeMove(Board board) {
    return board.newBoardWithMove(display.getMove(), board.currentMark());
  }
}
