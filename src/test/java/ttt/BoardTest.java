package ttt;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;

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
  public void itReturnsTheRows() {
    List<String> rowOne = asList(null, null, null);
    List<String> rowTwo = asList(null, null, null);
    List<String> rowThree = asList(null, null, null);
    List rows = board.getRows();

    assertEquals(rowOne, rows.get(0));
    assertEquals(rowTwo, rows.get(1));
    assertEquals(rowThree, rows.get(2));
 }

  @Test
  public void itReturnsTheRowWithAMark() {
    Board newBoard = board.newBoardWithMove(1, "X");
    List<String> rowOne = asList(null, "X", null);
    List rows = newBoard.getRows();

    assertEquals(rowOne, rows.get(0));
  }

  @Test
  public void itReturnsColumnWithMark() {
    Board newBoard = board.newBoardWithMove(4, "X");
    List<String> colTwo = asList(null, "X", null);
    List cols = newBoard.getColumns();

    assertEquals(colTwo, cols.get(1));
  }
}
