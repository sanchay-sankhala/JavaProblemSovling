package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class SearchMaze {
  @EpiUserType(ctorParams = {int.class, int.class})

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

  public enum Color { WHITE, BLACK }

  public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                            Coordinate s, Coordinate e) {
    // TODO - you fill in here.
    maze.get(s.x).set(s.y, Color.BLACK);
    List<Coordinate> visited = new ArrayList<>();
    visited.add(s);
    if (!findPath(maze, s, e, visited)) {
      visited.remove(visited.size() - 1);
    }
    return visited;
  }

  public static boolean findPath(List<List<Color>> maze, Coordinate s, Coordinate e, List<Coordinate> visited) {
    if (s.equals(e)) {
      return true;
    }
    final int[][] SHIFT = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    for (int[] sh : SHIFT) {
      Coordinate next = new Coordinate(s.x + sh[0], s.y + sh[1]);
      if (isValidMove(maze, next)) {
        maze.get(next.x).set(next.y, Color.BLACK);
        visited.add(next);
        if (findPath(maze, next, e, visited)) {
          return true;
        }
        visited.remove(visited.size() - 1);
      }
    }
    return false;
  }

  public static boolean isValidMove(List<List<Color>> maze, Coordinate n) {
    return n.x >= 0 && n.x < maze.size() && n.y >= 0 && n.y < maze.get(n.x).size() && maze.get(n.x).get(n.y) == Color.WHITE;
  }
  public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                              Coordinate prev, Coordinate cur) {
    if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
          cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
      return false;
    }
    return cur.x == prev.x + 1 && cur.y == prev.y ||
        cur.x == prev.x - 1 && cur.y == prev.y ||
        cur.x == prev.x && cur.y == prev.y + 1 ||
        cur.x == prev.x && cur.y == prev.y - 1;
  }

  @EpiTest(testDataFile = "search_maze.tsv")
  public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                          Coordinate s, Coordinate e)
      throws TestFailure {
    List<List<Color>> colored = new ArrayList<>();
    for (List<Integer> col : maze) {
      List<Color> tmp = new ArrayList<>();
      for (Integer i : col) {
        tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
      }
      colored.add(tmp);
    }
    List<Coordinate> path = searchMaze(colored, s, e);
    if (path.isEmpty()) {
      return s.equals(e);
    }

    if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
      throw new TestFailure("Path doesn't lay between start and end points");
    }

    for (int i = 1; i < path.size(); i++) {
      if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
        throw new TestFailure("Path contains invalid segments");
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
