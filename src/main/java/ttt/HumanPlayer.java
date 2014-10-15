package ttt;

public class HumanPlayer {
  public Board makeMove(Board board, int position) {
    return board.newBoardWithMove(position, board.currentMark());
  }
}
