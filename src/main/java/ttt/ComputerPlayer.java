package ttt;

public class ComputerPlayer implements Player {
  public static final double INITIAL_VALUE = 10.0;
  public static final int STARTING_DEPTH = 7;
  public static final int END_DEPTH = 0;
  public static final int SCORE_FOR_DRAW = 0;
  public static final int DEFAULT_BEST_MOVE = -1;

  @Override
  public Board makeMove(Board board) {
    return board.newBoardWithMove(bestMove(board), board.currentMark());
  }

  public int bestMove(Board board) {
    return negamax(board, STARTING_DEPTH, -INITIAL_VALUE, INITIAL_VALUE, board.currentMark()).getMove();
  }

  private MoveScore negamax(Board board, int depth, double alpha, double beta, String mark) {
    double bestValue = -INITIAL_VALUE;
    int bestMove = DEFAULT_BEST_MOVE;

    if (board.isOver() || depth == END_DEPTH) {
      double result = scoreFor(board, mark);
      return new MoveScore(result, bestMove);
    }

    for (Integer move : board.availableMoves()) {
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
    double score = INITIAL_VALUE / (board.getSizeOfGrid() - board.numberOfAvailableMoves());

    if (board.hasDraw()) {
      return SCORE_FOR_DRAW;
    } else if (board.lastMoveMark().equals(mark)) {
      return score;
    }

    return -score;
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
