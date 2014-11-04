package ttt;

public class ComputerPlayer implements Player {
  public String mark;

  public void chooseMark(Board board) {
    if (availableMovesCountIsEven(board)) {
      mark = "O";
    } else {
      mark = "X";
    }
  }

  @Override
  public Board makeMove(Board board) {
    if (mark == null) {
      chooseMark(board);
    }
    return board.newBoardWithMove(bestMove(board), mark);
  }

  public int bestMove(Board board) {
    return negamax(board, mark).getMove();
  }

  private MoveScore negamax(Board board, String mark) {
    double bestValue = -1.0;
    int bestMove = -1;

    if (board.isOver()) {
      double result = scoreFor(board, mark);
      return new MoveScore(result, bestMove);
    }

    for (Integer move : board.listOfMovesIndexOne()) {
      Board newBoard = board.newBoardWithMove(move, board.currentMark());
      double value = -negamax(newBoard, getOpponent(mark)).getScore();
      if (value > bestValue) {
        bestValue = value;
        bestMove = move;
      }
    }
    return new MoveScore(bestValue, bestMove);
  }

  private String getOpponent(String mark) {
    if (mark.equals("X")) {
      return "O";
    } else {
      return "X";
    }
  }

  private double scoreFor(Board board, String mark) {
    double score = 1.0 / (9 - board.numberOfAvailableMoves());

    if (board.hasDraw()) {
      return 0;
    } else if (board.lastMoveMark().equals(mark)) {
      return score;
    }

    return -score;
  }

  public String getMark() {
    return mark;
  }

  private boolean availableMovesCountIsEven(Board board) {
    return board.numberOfAvailableMoves() % 2 == 0;
  }

  private class MoveScore {
    private final double score;
    private final Integer move;

    public MoveScore(double score, Integer move) {
      this.score = score;
      this.move = move;
    }

    public double getScore() {
      return score;
    }

    public Integer getMove() {
      return move;
    }
  }
}
