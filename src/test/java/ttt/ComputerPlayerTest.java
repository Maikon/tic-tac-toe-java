package ttt;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

public class ComputerPlayerTest {

  @Test
  public void reachesTerminalNode() {
    Board board = new Board(asList("X", "O", "X",
                                   "X", "O", "X",
                                   "O", "X", "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(-1));
  }

  @Test
  public void goesForWinInRow() {
    Board board = new Board(asList("O", "" , "X",
                                   "" , "X", "X",
                                   "O", "" , "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(3));
  }

  @Test
  public void goesForWinInColumn() {
    Board board = new Board(asList("O", "X", "X",
                                   "" , "X", "" ,
                                   "O", "" , "O"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(7));
  }

  @Test
  public void goesForWinInDiagonal() {
    Board board = new Board(asList("X", "O", "",
                                   "O", "X", "",
                                   "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(8));
  }

  @Test
  public void blocksOpponentWinInRow() {
    Board board = new Board(asList("X", "" , "X",
                                   "O", "" , "" ,
                                   "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(1));
  }

  @Test
  public void blocksOpponentWinInColumn() {
    Board board = new Board(asList("X", "O", "X",
                                   "" , "O", "" ,
                                   "O", "X", "X"));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(5));
  }

  @Test
  public void blocksOpponentWinInDiagonal() {
    Board board = new Board(asList("" , "" , "X",
                                   "O", "X", "" ,
                                   "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(6));
  }

  @Test
  public void blocksDiagonalFork() {
    Board board = new Board(asList("" , "" , "X",
                                   "" , "O", "" ,
                                   "X", "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(1, 7);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }

  @Test
  public void blocksReverseDiagonalFork() {
    Board board = new Board(asList("X", "" , "",
                                   "" , "O", "",
                                   "" , "" , "X"));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(1, 7);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }

  @Test
  public void blocksAlternativeDiagonalFork() {
    Board board = new Board(asList("O", "" , "",
                                   "" , "X", "",
                                   "" , "" , "X"));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(2, 6);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }

  @Test
  public void blocksReverseAlternativeDiagonalFork() {
    Board board = new Board(asList("" , "" , "O",
                                   "" , "X", "",
                                   "X", "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(0, 8);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }

  @Test
  public void blocksEdgesFork() {
    Board board = new Board(asList("", "" , "" ,
                                   "", "O", "X",
                                   "", "X", ""));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(2, 6, 8);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }

  @Test
  public void blocksReverseEdgesFork() {
    Board board = new Board(asList("" , "X", "",
                                   "X", "O", "",
                                   "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(0, 2, 6);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }

  @Test
  public void shortDepthTestingFutureFork() {
    Board board = new Board(asList("X", "", "",
                                   "" , "", "",
                                   "" , "", ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(4));
  }

  @Test
  public void goesForWinInRow4x4() {
    Board board = new Board(asList("" , "X", "X", "X",
                                   "X", "O", "O", "" ,
                                   "O", "" , "" , "" ,
                                   "O", "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(0));
  }

  @Test
  public void goesForWinInColumn4x4() {
    Board board = new Board(asList("O", "X", "O", "X",
                                   "" , "X", "" , "" ,
                                   "O", "X" , "", "" ,
                                   "O", "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(13));
  }

  @Test
  public void goesForWinInDiagonal4x4() {
    Board board = new Board(asList("X", "O", "O", "X",
                                   "" , "X", "" , "" ,
                                   "O", "" , "X", "" ,
                                   "O", "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(15));
  }

  @Test
  public void blocksWinInDiagonal4x4() {
    Board board = new Board(asList("X", "O", "O", "X",
                                   "" , "X", "" , "" ,
                                   "O", "X", "X", "" ,
                                   "O", "" , "" , ""));
    ComputerPlayer computer = new ComputerPlayer();
    assertThat(computer.bestMove(board), is(15));
  }

  @Test
  public void blocksTrapOn4x4() {
    Board board = new Board(asList("",   "X",  "",  "",
                                   "O",  "X", "O", "",
                                   "",   "",  "",  "O",
                                   "X",  "",  "",  "X"));
    ComputerPlayer computer = new ComputerPlayer();
    List goodMoves = asList(9, 13, 14);
    assertThat(computer.bestMove(board), isIn(goodMoves));
  }
}
