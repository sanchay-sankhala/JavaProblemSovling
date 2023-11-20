package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IsStringInMatrix {
  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    // TODO - you fill in here.
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        if (grid.get(i).get(j) == pattern.get(0) && isPatternPresent(i, j, 1, grid, pattern)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isPatternPresent(int i, int j, int p, List<List<Integer>> grid, List<Integer> pattern) {
    if (p == pattern.size()) {
      return true;
    }
    final int[][]SHIFT = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    for (int[] s : SHIFT) {
      int x = i + s[0];
      int y = j + s[1];
      if (x >= 0 && x < grid.size() && y >= 0 && y < grid.get(0).size() && grid.get(x).get(y) == pattern.get(p)) {
        if (isPatternPresent(x, y, p + 1, grid, pattern)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
