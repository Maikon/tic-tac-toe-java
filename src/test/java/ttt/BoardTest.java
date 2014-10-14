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
  public void itReturnsAllCombinations() {
    List<List<String>> combinations = board.getCombinations();

    assertEquals(8, combinations.size());
  }

  @Test
  public void itReturnsFalseIfBoardDoesNotHaveWinner() {
    assertEquals(false, board.hasWinner());
  }

  @Test
  public void itReturnsTrueIfBoardHasWinner() {
    Board board = new Board(asList("X", "X", "X", null, null, null, null, null, null));
    assertEquals(true, board.hasWinner());
  }
}
