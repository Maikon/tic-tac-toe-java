package ttt;

import org.junit.Before;
import org.junit.Test;
import ttt.Exceptions.InvalidMoveException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

  int defaultAvailableMoves = 9;
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void itHasNineMoves() {
    assertThat(board.numberOfAvailableMoves(), equalTo(defaultAvailableMoves));
  }

  @Test
  public void itReturnsNewBoardWithMarkedMoves() {
    Board boardWithOneMove = board.newBoardWithMove(7, "X");
    Board boardWithTwoMoves = boardWithOneMove.newBoardWithMove(8, "X");
    assertThat(boardWithTwoMoves.numberOfAvailableMoves(), equalTo(defaultAvailableMoves - 2));
  }

  @Test
  public void itReturnsAListOfAvailableMoves() {
    List<Integer> moves = asList(0, 1, 2, 3, 4, 5, 6, 8);
    Board boardWithOneMove = board.newBoardWithMove(7, "X");
    assertThat(boardWithOneMove.listOfMoves(), equalTo(moves));
  }

  @Test
  public void itReturnsAllCombinations() {
    List<Line> combinations = board.getCombinations();

    assertThat(combinations.size(), equalTo(8));
  }

  @Test
  public void itReturnsFalseIfBoardDoesNotHaveWinner() {
    assertThat(board.hasWinner(), equalTo(false));
  }

  @Test
  public void itReturnsTrueIfBoardHasWinner() {
    Board board = new Board(asList("X", "X", "X", "", "", "", "", "", ""));
    assertThat(board.hasWinner(), equalTo(true));
  }

  @Test
  public void itReturnsFalseIfNotADraw() {
    assertThat(board.hasDraw(), equalTo(false));
  }

  @Test
  public void itReturnsTrueIfADraw() {
    Board board = new Board(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    assertThat(board.hasDraw(), equalTo(true));
  }

  @Test
  public void itReturnsFalseIfBoardIsNotInEndState() {
    assertThat(board.isOver(), equalTo(false));
  }

  @Test
  public void itReturnsFalseIfBoardIsInEndState() {
    Board boardWithDraw = new Board(asList("X", "O", "X", "O", "O", "X", "O", "X", "O"));
    Board boardWithWin = new Board(asList("X", "X", "X", "", "", "", "", "", ""));

    assertThat(boardWithDraw.isOver(), equalTo(true));
    assertThat(boardWithWin.isOver(), equalTo(true));
  }

  @Test
  public void itReturnsCurrentMarkWhenBoardMovesIsOdd() {
    assertThat(board.currentMark(), equalTo("X"));
  }

  @Test
  public void itReturnsCurrentMarkWhenBoardMovesIsEven() {
    Board boardWithMove = board.newBoardWithMove(0, "X");
    assertThat(boardWithMove.currentMark(), equalTo("O"));
  }

  @Test
  public void itReturnsMarkOfLastMoveMade() {
    Board boardWithTwoMoves = new Board(asList("X", "O", "", "", "", "", "", "", ""));
    assertThat(boardWithTwoMoves.lastMoveMark(), equalTo("O"));
  }

  @Test(expected = InvalidMoveException.class)
  public void itRaisesAnExceptionForAnInvalidMove() {
    Board boardWithMove = board.newBoardWithMove(0, "X");
    boardWithMove.newBoardWithMove(0, "O");
  }
}
