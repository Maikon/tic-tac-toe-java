package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Exceptions.InvalidMoveException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void supportsThreeByThree() {
    assertThat(board.numberOfAvailableMoves(), is(9));
  }

  @Test
  public void supportsFourByFour() {
    Board board = new Board(4);
    assertThat(board.numberOfAvailableMoves(), is(16));
  }

  @Test
  public void makeMoveOnFourByFour() {
    Board board = new Board(4);
    Board boardWithMove = new Board(asList("", "", "", "",
                                           "", "", "", "",
                                           "", "", "", "",
                                           "", "", "X", ""));
    Board newBoard = board.newBoardWithMove(15, "X");
    assertThat(newBoard.getGrid(), equalTo(boardWithMove.getGrid()));
  }

  @Test
  public void checksForWinnerInRowsOnFourByFour() {
    Board boardRowOne = new Board(asList("X", "X", "X", "X",
                                         "", "", "", "",
                                         "", "", "", "",
                                         "", "", "", ""));
    Board boardRowTwo = new Board(asList("", "", "", "",
                                         "X", "X", "X", "X",
                                         "", "", "", "",
                                         "", "", "", ""));
    Board boardRowThree = new Board(asList("", "", "", "",
                                           "", "", "", "",
                                           "X", "X", "X", "X",
                                           "", "", "", ""));
    Board boardRowFour = new Board(asList("", "", "", "",
                                          "", "", "", "",
                                          "", "", "", "",
                                          "X", "X", "X", "X"));
    assertThat(boardRowOne.hasWinner(), is(true));
    assertThat(boardRowTwo.hasWinner(), is(true));
    assertThat(boardRowThree.hasWinner(), is(true));
    assertThat(boardRowFour.hasWinner(), is(true));
  }

  @Test
  public void checksForWinnerInColumnsOnFourByFour() {
    Board boardColOne = new Board(asList("X", "", "", "",
                                         "X", "", "", "",
                                         "X", "", "", "",
                                         "X", "", "", ""));
    Board boardColTwo = new Board(asList("", "X", "", "",
                                         "", "X", "", "",
                                         "", "X", "", "",
                                         "", "X", "", ""));
    Board boardColThree = new Board(asList("", "", "X", "",
                                           "", "", "X", "",
                                           "", "", "X", "",
                                           "", "", "X", ""));
    Board boardColFour = new Board(asList("", "", "", "X",
                                          "", "", "", "X",
                                          "", "", "", "X",
                                          "", "", "", "X"));
    assertThat(boardColOne.hasWinner(), is(true));
    assertThat(boardColTwo.hasWinner(), is(true));
    assertThat(boardColThree.hasWinner(), is(true));
    assertThat(boardColFour.hasWinner(), is(true));
  }

  @Test
  public void checksForWinnerInDiagonalsOnFourByFour() {
    Board boardDiagonalOne = new Board(asList("X", "", "", "",
                                              "", "X", "", "",
                                              "", "", "X", "",
                                              "", "", "", "X"));
    Board boardDiagonalTwo = new Board(asList("", "", "", "X",
                                              "", "", "X", "",
                                              "", "X", "", "",
                                              "X", "", "", ""));
    assertThat(boardDiagonalOne.hasWinner(), is(true));
    assertThat(boardDiagonalTwo.hasWinner(), is(true));
  }

  @Test
  public void newBoardWithMovesMade() {
    Board boardWithOneMove = board.newBoardWithMove(7, "X");
    Board boardWithTwoMoves = boardWithOneMove.newBoardWithMove(8, "X");
    assertThat(boardWithTwoMoves.numberOfAvailableMoves(), is(7));
  }

  @Test
  public void listOfAvailableMoves() {
    List<Integer> moves = asList(0, 1, 2, 3, 4, 5, 6, 8);
    Board boardWithOneMove = board.newBoardWithMove(8, "X");
    assertThat(boardWithOneMove.listOfMoves(), equalTo(moves));
  }

  @Test
  public void allCombinations() {
    List<Line> combinations = board.getCombinations();

    assertThat(combinations.size(), is(8));
  }

  @Test
  public void whenBoardDoesNotHaveWinner() {
    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void whenBoardHasWinnerInRows() {
    Board boardFirstRowWin = new Board(asList("X", "X", "X",
                                              "", "", "",
                                              "", "", ""));

    Board boardSecondRowWin = new Board(asList("", "", "",
                                               "X", "X", "X",
                                               "", "", ""));

    Board boardThirdRowWin = new Board(asList("", "", "",
                                              "", "", "",
                                              "X", "X", "X"));

    assertThat(boardFirstRowWin.hasWinner(), is(true));
    assertThat(boardSecondRowWin.hasWinner(), is(true));
    assertThat(boardThirdRowWin.hasWinner(), is(true));
  }

  @Test
  public void whenBoardHasWinnerInColumns() {
    Board boardFirstColWin = new Board(asList("X", "", "",
                                              "X", "", "",
                                              "X", "", ""));

    Board boardSecondColWin = new Board(asList("", "X", "",
                                               "", "X", "",
                                               "", "X", ""));

    Board boardThirdColWin = new Board(asList("", "", "X",
                                              "", "", "X",
                                              "", "", "X"));

    assertThat(boardFirstColWin.hasWinner(), is(true));
    assertThat(boardSecondColWin.hasWinner(), is(true));
    assertThat(boardThirdColWin.hasWinner(), is(true));
  }

  @Test
  public void whenBoardHasWinnerInDiagonals() {
    Board leftDiagonalWin = new Board(asList("X", "", "",
                                             "", "X", "",
                                             "", "", "X"));

    Board rightDiagonalWin = new Board(asList("", "", "X",
                                              "", "X", "",
                                              "X", "", ""));

    assertThat(leftDiagonalWin.hasWinner(), is(true));
    assertThat(rightDiagonalWin.hasWinner(), is(true));
  }

  @Test
  public void whenIsNotADraw() {
    assertThat(board.hasDraw(), is(false));
  }

  @Test
  public void whenIsADraw() {
    Board board = new Board(asList("X", "O", "X",
                                   "X", "O", "X",
                                   "O", "X", "O"));
    assertThat(board.hasDraw(), is(true));
  }

  @Test
  public void whenNotInEndState() {
    assertThat(board.isOver(), is(false));
  }

  @Test
  public void whenInEndState() {
    Board boardWithDraw = new Board(asList("X", "O", "X",
                                           "O", "O", "X",
                                           "O", "X", "O"));
    Board boardWithWin = new Board(asList("X", "X", "X",
                                          "", "", "",
                                          "", "", ""));

    assertThat(boardWithDraw.isOver(), is(true));
    assertThat(boardWithWin.isOver(), is(true));
  }

  @Test
  public void currentMarkWhenBoardMovesIsOdd() {
    assertThat(board.currentMark(), is("X"));
  }

  @Test
  public void currentMarkWhenBoardMovesIsEven() {
    Board boardWithMove = board.newBoardWithMove(1, "X");
    assertThat(boardWithMove.currentMark(), is("O"));
  }

  @Test
  public void markOfLastMoveMade() {
    Board boardWithTwoMoves = new Board(asList("X", "O", "",
                                               "", "", "",
                                               "", "", ""));
    assertThat(boardWithTwoMoves.lastMoveMark(), is("O"));
  }

  @Test(expected = InvalidMoveException.class)
  public void raisesAnExceptionForAnInvalidMove() {
    Board boardWithMove = board.newBoardWithMove(0, "X");
    boardWithMove.newBoardWithMove(0, "O");
  }
}
