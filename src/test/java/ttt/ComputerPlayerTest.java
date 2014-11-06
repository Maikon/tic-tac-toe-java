package ttt;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComputerPlayerTest {

  @Test
  public void getsXasMarkIfAvailableMovesIsOdd() {
    Board board = new Board();
    ComputerPlayer computer = new ComputerPlayer();
    computer.chooseMark(board);
    assertThat(computer.getMark(), is("X"));
  }

  @Test
  public void getsOasMarkIfAvailableMovesIsEven() {
    Board board = new Board(asList("X", "", "",
                                   "", "", "",
                                   "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    computer.chooseMark(board);
    assertThat(computer.getMark(), is("O"));
  }

  @Test
  public void reachesTerminalNode() {
    Board board = new Board(asList("X", "O", "X",
                                   "X", "O", "X",
                                   "O", "X", "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(-1));
  }

  @Test
  public void goesForWinScenarioOne() {
    Board board = new Board(asList("O", "", "X",
                                   "", "X", "X",
                                   "O", "", "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(4));
  }

  @Test
  public void goesForWinScenarioTwo() {
    Board board = new Board(asList("X", "O", "",
                                   "O", "X", "",
                                   "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(9));
  }

  @Test
  public void blocksOpponentWinScenarioOne() {
    Board board = new Board(asList("", "", "X",
                                   "O", "X", "",
                                   "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(7));
  }

  @Test
  public void blocksOpponentWinScenarioTwo() {
    Board board = new Board(asList("X", "O", "X",
                                   "", "O", "",
                                   "O", "X", "X"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(6));
  }

  @Test
  public void blocksDiagonalFork() {
    Board board = new Board(asList("", "", "X",
                                   "", "O", "",
                                   "X", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(2));
  }

  @Test
  public void blocksReverseDiagonalFork() {
    Board board = new Board(asList("X", "", "",
                                   "", "O", "",
                                   "", "", "X"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(2));
  }

  @Test
  public void blocksEdgesFork() {
    Board board = new Board(asList("", "", "",
                                   "", "O", "X",
                                   "", "X", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(3));
  }

  @Test
  public void blocksReverseEdgesFork() {
    Board board = new Board(asList("", "X", "",
                                   "X", "O", "",
                                   "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(1));
  }

  @Test
  public void blocksAlternativeDiagonalFork() {
    Board board = new Board(asList("O", "", "",
                                   "", "X", "",
                                   "", "", "X"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(3));
  }

  @Test
  public void blocksReverseAlternativeDiagonalFork() {
    Board board = new Board(asList("", "", "O",
                                   "", "X", "",
                                   "X", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(1));
  }
}
