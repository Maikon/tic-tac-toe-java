package ttt;

public class ComputerPlayer implements Player {
  public String mark;

  @Override
  public Board makeMove(Board board) {
    return board.newBoardWithMove(bestMove(board), mark);
  }

  public int bestMove(Board board) {
    if (mark == null) {
      chooseMark(board);
    }
    return negamax(board, 7, -10.0, 10.0, mark).getMove();
  }

  private void chooseMark(Board board) {
    if (availableMovesCountIsEven(board)) {
      mark = "O";
    } else {
      mark = "X";
    }
  }

  private MoveScore negamax(Board board, int depth, double alpha, double beta, String mark) {
    double bestValue = -10.0;
    int bestMove = -1;

    if (board.isOver() || depth == 0) {
      double result = scoreFor(board, mark);
      return new MoveScore(result, bestMove);
    }

    for (Integer move : board.listOfMovesIndexOne()) {
      Board newBoard = board.newBoardWithMove(move, board.currentMark());
      double value = -negamax(newBoard, depth - 1,  -beta, -alpha, getOpponent(mark)).getScore();
      if (value > bestValue) {
        bestValue = value;
        bestMove = move;
      }
      if (value > alpha) {
        alpha = value;
      }
      if (alpha >= beta) {
        break;
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
    double score = 10.0 / (board.getGrid().size() - board.numberOfAvailableMoves());

    if (board.hasDraw()) {
      return 0;
    } else if (board.lastMoveMark().equals(mark)) {
      return score;
    }

    return -score;
  }

  private boolean availableMovesCountIsEven(Board board) {
    return board.numberOfMovesMade() % 2 == 0;
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
