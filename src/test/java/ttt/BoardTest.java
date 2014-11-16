package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Exceptions.InvalidMoveException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

  private Board board3x3;
  private Board board4x4;

  @Before
  public void setup() {
    board3x3 = new Board(3);
    board4x4 = new Board(4);
  }

  @Test
  public void supports3x3() {
    assertThat(board3x3.numberOfAvailableMoves(), is(9));
  }

  @Test
  public void supports4x4() {
    assertThat(board4x4.numberOfAvailableMoves(), is(16));
  }

  @Test
  public void makeMoveOn4x4() {
    Board boardWithMove = createBoard(4, asList(15));
    Board newBoard = board4x4.newBoardWithMove(15, "X");

    assertThat(newBoard.getGrid(), equalTo(boardWithMove.getGrid()));
  }

  @Test
  public void checksForWinnerInRowsOn4x4() {
    Board boardRowOne   = createBoard(4, asList(0, 1, 2, 3));
    Board boardRowTwo   = createBoard(4, asList(4, 5, 6, 7));
    Board boardRowThree = createBoard(4, asList(8, 9, 10, 11));
    Board boardRowFour  = createBoard(4, asList(12, 13, 14, 15));

    assertThat(boardRowOne.hasWinner(),   is(true));
    assertThat(boardRowTwo.hasWinner(),   is(true));
    assertThat(boardRowThree.hasWinner(), is(true));
    assertThat(boardRowFour.hasWinner(),  is(true));
  }

  @Test
  public void checksForWinnerInColumnsOn4x4() {
    Board boardColOne   = createBoard(4, asList(0, 4, 8, 12));
    Board boardColTwo   = createBoard(4, asList(1, 5, 9, 13));
    Board boardColThree = createBoard(4, asList(2, 6, 10, 14));
    Board boardColFour  = createBoard(4, asList(3, 7, 11, 15));

    assertThat(boardColOne.hasWinner(),   is(true));
    assertThat(boardColTwo.hasWinner(),   is(true));
    assertThat(boardColThree.hasWinner(), is(true));
    assertThat(boardColFour.hasWinner(),  is(true));
  }

  @Test
  public void checksForWinnerInDiagonalsOn4x4() {
    Board boardDiagonalOne = createBoard(4, asList(0, 5, 10, 15));
    Board boardDiagonalTwo = createBoard(4, asList(3, 6, 9, 12));

    assertThat(boardDiagonalOne.hasWinner(), is(true));
    assertThat(boardDiagonalTwo.hasWinner(), is(true));
  }

  @Test
  public void newBoardWithMovesMade3x3() {
    Board boardWithMove3x3 = createBoard(3, asList(6));

    assertThat(boardWithMove3x3.numberOfAvailableMoves(), is(8));
  }

  @Test
  public void newBoardWithMovesMade4x4() {
    Board boardWithMove4x4 = createBoard(4, asList(9, 4));

    assertThat(boardWithMove4x4.numberOfAvailableMoves(), is(14));
  }

  @Test
  public void listOfAvailableMoves3x3() {
    Board boardWithOneMove = createBoard(3, asList(4));

    assertThat(boardWithOneMove.availableMoves().size(), is(8));
  }

  @Test
  public void listOfAvailableMoves4x4() {
    Board boardWithOneMove = createBoard(4, asList(10));

    assertThat(boardWithOneMove.availableMoves().size(), is(15));
  }

  @Test
  public void boardDoesNotHaveWinner3x3() {
    assertThat(board3x3.hasWinner(), is(false));
  }

  @Test
  public void boardDoesNotHaveWinner4x4() {
    assertThat(board4x4.hasWinner(), is(false));
  }

  @Test
  public void checksForWinnerInRowsOn3x3() {
    Board boardFirstRowWin  = createBoard(3, asList(0, 1, 2));
    Board boardSecondRowWin = createBoard(3, asList(3, 4, 5));
    Board boardThirdRowWin  = createBoard(3, asList(6, 7, 8));

    assertThat(boardFirstRowWin.hasWinner(),  is(true));
    assertThat(boardSecondRowWin.hasWinner(), is(true));
    assertThat(boardThirdRowWin.hasWinner(),  is(true));
  }

  @Test
  public void checksForWinnerInColumnsOn3x3() {
    Board boardFirstColWin  = createBoard(3, asList(0, 3, 6));
    Board boardSecondColWin = createBoard(3, asList(1, 4, 7));
    Board boardThirdColWin  = createBoard(3, asList(2, 5, 8));

    assertThat(boardFirstColWin.hasWinner(),  is(true));
    assertThat(boardSecondColWin.hasWinner(), is(true));
    assertThat(boardThirdColWin.hasWinner(),  is(true));
  }

  @Test
  public void checksForWinnerInDiagonalsOn3x3() {
    Board leftDiagonalWin = createBoard(3, asList(0, 4, 8));
    Board rightDiagonalWin = createBoard(3, asList(2, 4, 6));

    assertThat(leftDiagonalWin.hasWinner(),  is(true));
    assertThat(rightDiagonalWin.hasWinner(), is(true));
  }

  @Test
  public void boardDoesNotHaveADraw3x3() {
    assertThat(board3x3.hasDraw(), is(false));
  }

  @Test
  public void boardDoesNotHaveADraw4x4() {
    assertThat(board4x4.hasDraw(), is(false));
  }

  @Test
  public void boardDoesHaveADraw3x3() {
    assertThat(getBoardWithDraw3x3().hasDraw(), is(true));
  }

  @Test
  public void boardDoesHaveADraw4x4() {
    assertThat(getBoardWithDraw4x4().hasDraw(), is(true));
  }

  @Test
  public void boardNotInEndState3x3() {
    assertThat(board3x3.isOver(), is(false));
  }

  @Test
  public void boardNotInEndState4x4() {
    assertThat(board4x4.isOver(), is(false));
  }

  @Test
  public void boardInEndState3x3() {
    assertThat(getBoardWithDraw3x3().isOver(), is(true));
    assertThat(getWinningBoard3x3().isOver(),  is(true));
  }

  @Test
  public void boardInEndState4x4() {
    assertThat(getBoardWithDraw4x4().isOver(), is(true));
    assertThat(getWinningBoard4x4().isOver(),  is(true));
  }

  @Test
  public void currentMarkOnEmpty3x3() {
    assertThat(board3x3.currentMark(), is("X"));
  }

  @Test
  public void currentMarkOn3x3ThatHasMove() {
    Board boardWithMove = createBoard(3, asList(5));

    assertThat(boardWithMove.currentMark(), is("O"));
  }

  @Test
  public void currentMarkOnEmpty4x4() {
    assertThat(board4x4.currentMark(), is("X"));
  }

  @Test
  public void currentMarkOn4x4ThatHasMove() {
    Board boardWithMove = createBoard(4, asList(10));
    assertThat(boardWithMove.currentMark(), is("O"));
  }

  @Test
  public void markOfLastMoveMade() {
    Board boardWithTwoMoves = createBoard(3, asList(1));

    assertThat(boardWithTwoMoves.lastMoveMark(), is("X"));
  }

  @Test(expected = InvalidMoveException.class)
  public void raisesAnExceptionForAnInvalidMove() {
    Board boardWithMove = createBoard(3, asList(1));
    boardWithMove.newBoardWithMove(1, "O");
  }

  private Board createBoard(int size, List<Integer> moves) {
    Board board = new Board(size);
    for (int move : moves) {
      board = board.newBoardWithMove(move, "X");
    }
    return board;
  }

  private Board getBoardWithDraw3x3() {
    return new Board(asList("X", "O", "X",
                            "X", "O", "X",
                            "O", "X", "O"));
  }

  private Board getWinningBoard3x3() {
    return new Board(asList("X", "X", "X",
                            "" , "" , "" ,
                            "" , "" , ""));
  }

  private Board getBoardWithDraw4x4() {
    return new Board(asList("X", "O", "X", "O",
                            "X", "O", "X", "O",
                            "O", "X", "O", "X",
                            "O", "X", "O", "X"));
  }

  private Board getWinningBoard4x4() {
    return new Board(asList("X", "" , "" , "",
                            "" , "X", "" , "",
                            "" , "" , "X", "",
                            "" , "" , "" , "X"));
  }
}
