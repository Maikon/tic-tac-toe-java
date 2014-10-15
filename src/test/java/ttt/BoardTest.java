package ttt;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class BoardTest {

  int defaultAvailableMoves = 9;
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void itHasNineMoves() {
    assertEquals(defaultAvailableMoves, board.numberOfAvailableMoves());
  }

  @Test
  public void itReturnsNewBoardWithMarkedMoves() {
    Board boardWithOneMove = board.newBoardWithMove(7, "X");
    Board boardWithTwoMoves = boardWithOneMove.newBoardWithMove(8, "X");
    assertEquals(defaultAvailableMoves - 2, boardWithTwoMoves.numberOfAvailableMoves());
  }

  @Test
  public void itReturnsAListOfAvailableMoves() {
    List<Integer> moves = asList(0, 1, 2, 3, 4, 5, 6, 8);
    Board boardWithOneMove = board.newBoardWithMove(7, "X");
    assertEquals(moves, boardWithOneMove.listOfMoves());
  }

  @Test
  public void itReturnsAllCombinations() {
    List<Line> combinations = board.getCombinations();

    assertEquals(8, combinations.size());
  }

  @Test
  public void itReturnsFalseIfBoardDoesNotHaveWinner() {
    assertEquals(false, board.hasWinner());
  }

  @Test
  public void itReturnsTrueIfBoardHasWinner() {
    Board board = new Board(asList("X", "X", "X", "", "", "", "", "", ""));
    assertEquals(true, board.hasWinner());
  }

  @Test
  public void itReturnsFalseIfNotADraw() {
    assertEquals(false, board.hasDraw());
  }

  @Test
  public void itReturnsTrueIfADraw() {
    Board board = new Board(asList("X", "O", "X", "O", "O", "X", "O", "X", "O"));
    assertEquals(true, board.hasDraw());
  }

  @Test
  public void itReturnsFalseIfBoardIsNotInEndState() {
    assertEquals(false, board.isOver());
  }

  @Test
  public void itReturnsFalseIfBoardIsInEndState() {
    Board boardWithDraw = new Board(asList("X", "O", "X", "O", "O", "X", "O", "X", "O"));
    Board boardWithWin = new Board(asList("X", "X", "X", "", "", "", "", "", ""));

    assertEquals(true, boardWithDraw.isOver());
    assertEquals(true, boardWithWin.isOver());
  }

  @Test
  public void itReturnsCurrentMarkWhenBoardMovesIsOdd() {
    assertEquals("X", board.currentMark());
  }

  @Test
  public void itReturnsCurrentMarkWhenBoardMovesIsEven() {
    Board boardWithMove = board.newBoardWithMove(0, "X");
    assertEquals("O", boardWithMove.currentMark());
  }

  @Test
  public void itReturnsMarkOfLastMoveMade() {
    Board boardWithTwoMoves = new Board(asList("X", "O", "", "", "", "", "", "", ""));
    assertEquals("O", boardWithTwoMoves.lastMoveMark());
  }
}
