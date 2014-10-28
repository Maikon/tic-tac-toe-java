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

  int defaultAvailableMoves = 9;
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void hasNineMoves() {
    assertThat(board.numberOfAvailableMoves(), equalTo(defaultAvailableMoves));
  }

  @Test
  public void newBoardWithMovesMade() {
    Board boardWithOneMove = board.newBoardWithMove(7, "X");
    Board boardWithTwoMoves = boardWithOneMove.newBoardWithMove(8, "X");
    assertThat(boardWithTwoMoves.numberOfAvailableMoves(), equalTo(defaultAvailableMoves - 2));
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
  public void whenBoardHasWinner() {
    Board board = new Board(asList("X", "X", "X", "", "", "", "", "", ""));
    assertThat(board.hasWinner(), is(true));
  }

  @Test
  public void whenIsNotADraw() {
    assertThat(board.hasDraw(), is(false));
  }

  @Test
  public void whenIsADraw() {
    Board board = new Board(asList("X", "O", "X", "X", "O", "X", "O", "X", "O"));
    assertThat(board.hasDraw(), is(true));
  }

  @Test
  public void whenNotInEndState() {
    assertThat(board.isOver(), is(false));
  }

  @Test
  public void whenInEndState() {
    Board boardWithDraw = new Board(asList("X", "O", "X", "O", "O", "X", "O", "X", "O"));
    Board boardWithWin = new Board(asList("X", "X", "X", "", "", "", "", "", ""));

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
    Board boardWithTwoMoves = new Board(asList("X", "O", "", "", "", "", "", "", ""));
    assertThat(boardWithTwoMoves.lastMoveMark(), is("O"));
  }

  @Test(expected = InvalidMoveException.class)
  public void raisesAnExceptionForAnInvalidMove() {
    Board boardWithMove = board.newBoardWithMove(0, "X");
    boardWithMove.newBoardWithMove(0, "O");
  }
}
