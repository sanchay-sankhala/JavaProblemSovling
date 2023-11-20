package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    int[][] dp = new int[A.length() + 1][B.length() + 1];
    for (int i = 0; i < A.length() + 1; i++) {
      for (int j = 0; j < B.length() + 1; j++) {
        if (i == 0) {
          dp[i][j] = j;
        }
        else if (j == 0) {
          dp[i][j] = i;
        }
        else if (A.charAt(i-1) == B.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1];
        }
        else {
          dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
        }
      }
    }
    return dp[A.length()][B.length()];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
