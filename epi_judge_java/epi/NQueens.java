package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class NQueens {
  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> result = new ArrayList<>();
    solveNQueens(n, 0 , new ArrayList<Integer>(), result);
    return result;
  }

  public static void solveNQueens(int n, int row, List<Integer> colPlacements, List<List<Integer>> result) {
    if (n == row) {
      result.add(new ArrayList<>(colPlacements));
    }
    else {
      for (int i = 0; i < n; i++) {
        colPlacements.add(i);
        if (isValid(colPlacements)) {
          solveNQueens(n, row + 1, colPlacements, result);
        }
        colPlacements.remove(colPlacements.size() - 1);
      }
    }
  }

  public static boolean isValid(List<Integer> colPlacements) {
    int rowId = colPlacements.size() - 1;
    for (int i = 0; i < rowId; i++) {
      int diff  = Math.abs(colPlacements.get(rowId) - colPlacements.get(i));
      if (diff == 0 || diff == rowId - i) {
        return false;
      }
    }
    return true;
  }
  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
