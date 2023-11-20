package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixEnclosedRegions {

  public static void fillSurroundedRegions(List<List<Character>> board) {
    // TODO - you fill in here.
    Set<Coordinate> boundaryW = new HashSet();

    //first and last column
    for (int i = 0; i < board.size(); i ++) {
      if (board.get(i).get(0).equals('W') && !boundaryW.contains(new Coordinate(i, 0))) {
        findRegion(new Coordinate(i, 0), board, boundaryW);
      }
      if (board.get(i).get(board.get(0).size()-1).equals('W') && !boundaryW.contains(new Coordinate(i, board.get(0).size()-1))) {
        findRegion(new Coordinate(i, board.get(0).size()-1), board, boundaryW);
      }
    }
    for (int i = 0; i < board.get(0).size(); i ++) {
      if (board.get(0).get(i).equals('W') && !boundaryW.contains(new Coordinate(0, i))) {
        findRegion(new Coordinate(0, i), board, boundaryW);
      }
      if (board.get(board.size() - 1).get(i).equals('W') && !boundaryW.contains(new Coordinate(board.size() - 1, i))) {
        findRegion(new Coordinate(board.size() - 1, i), board, boundaryW);
      }
    }
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(0).size(); j++) {
        if (board.get(i).get(j).equals('W') && !boundaryW.contains(new Coordinate(i, j))) {
          board.get(i).set(j, 'B');
        }
      }
    }
    return;
  }

  public static void findRegion(Coordinate cur, List<List<Character>> board, Set<Coordinate> visited) {
    visited.add(cur);
    final int[][] SHIFT = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    for (int[] s : SHIFT) {
      Coordinate next = new Coordinate(cur.x + s[0], cur.y + s[1]);
      if (isValidMove(board, next, visited)) {
        findRegion(next, board, visited);
      }
    }
  }

  public static boolean isValidMove(List<List<Character>> maze, Coordinate n, Set<Coordinate> visited) {
    return n.x >= 0 && n.x < maze.size() && n.y >= 0 && n.y < maze.get(n.x).size() && maze.get(n.x).get(n.y) == 'W' && !visited.contains(n);
  }

  public static class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate)o;
      if (x != that.x || y != that.y) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }
  }
  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
