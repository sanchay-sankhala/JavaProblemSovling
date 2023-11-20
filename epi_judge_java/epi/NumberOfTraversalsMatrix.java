package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    int[] prev = new int[m];
    for (int i = 0; i < n; i++) {
      int[] cur = new int[m];
      for (int j = 0; j < m; j++) {
        if (j == 0 || i == 0) {
          cur[j] = 1;
        }
        else {
          int fromTop = prev[j] ;
          int fromLeft = cur[j-1];
          cur[j] = fromTop + fromLeft;
        }
      }
      prev = cur;
    }
    return prev[m-1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
