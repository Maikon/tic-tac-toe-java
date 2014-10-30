package ttt;

import org.junit.Ignore;
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
    Board board = new Board(asList("X", "", "", "", "", "", "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    computer.chooseMark(board);
    assertThat(computer.getMark(), is("O"));
  }

  @Test
  public void reachesTerminalNoe() {
    Board board = new Board(asList("X", "O", "X",
                                   "X", "O", "X",
                                   "O", "X", "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(-1));
  }

  @Test
  public void blocksAWin() {
    Board board = new Board(asList("X", "X", "",
                                   "O", "", "",
                                   "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(2));
  }

  @Test
  public void goesForTheWin() {
    Board board = new Board(asList("X", "O", "",
                                   "X", "O", "",
                                   "", "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(6));
  }

  @Test
  public void blocksDiagonalFork() {
    Board board = new Board(asList("X", "", "",
                                   "", "O", "",
                                   "", "", "X"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(1));
  }

  @Test
  public void blocksAlternativeDiagonalFork() {
    Board board = new Board(asList("X", "", "",
                                   "", "X", "",
                                   "", "", "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(1));
  }

  @Ignore
  public void blocksEdgesFork() {
    Board board = new Board(asList("", "", "",
                                   "", "O", "X",
                                   "", "X", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(8));
  }
}
