package ttt;

public class ComputerPlayer {
  private String mark;
  private String opponent;

  public void chooseMark(Board board) {
    if (availableMovesCountIsEven(board)) {
      mark = "O";
      opponent = "X";
    } else {
      mark = "X";
      opponent = "O";
    }
  }

  public int bestMove(Board board) {
    if (mark == null) {
      chooseMark(board);
    }
    MoveScore bestScore = getHighestRatedMove(board);
    return bestScore.getMove();
  }

  private MoveScore getHighestRatedMove(Board board) {
    MoveScore bestScoreAndMove = new MoveScore(-10, -1);
    for (Integer move : board.listOfMoves()) {
      Board newBoard = board.newBoardWithMove(move + 1, mark);
      if (newBoard.winFor(mark)) {
        if (scoreForWin(newBoard) > bestScoreAndMove.getScore()) {
          bestScoreAndMove = new MoveScore(scoreForWin(newBoard), move);
        }
      } else if (newBoard.winFor(opponent)) {
        if (-scoreForWin(newBoard) > bestScoreAndMove.getScore()) {
          bestScoreAndMove = new MoveScore(-scoreForWin(newBoard), move);
        }
      } else if (newBoard.hasDraw()) {
        if (0 > bestScoreAndMove.getScore()) {
          bestScoreAndMove = new MoveScore(0, move);
        }
      } else {
        if (getHighestRatedMove(newBoard).getScore() > bestScoreAndMove.getScore()) {
          bestScoreAndMove = new MoveScore(getHighestRatedMove(newBoard).getScore(), move);
        }
      }
    }
    return bestScoreAndMove;
  }

  private int scoreForWin(Board board) {
    return 10 + board.numberOfAvailableMoves();
  }

  public String getMark() {
    return mark;
  }

  private boolean availableMovesCountIsEven(Board board) {
    return board.numberOfAvailableMoves() % 2 == 0;
  }

  private class MoveScore {
    private final Integer score;
    private final Integer move;

    public MoveScore(Integer score, Integer move) {
      this.score = score;
      this.move = move;
    }

    public Integer getScore() {
      return score;
    }

    public Integer getMove() {
      return move;
    }
  }
}
