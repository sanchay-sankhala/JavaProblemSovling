package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    // TODO - you fill in here.
    List<Integer> numOfCombinations = new ArrayList<>(finalScore + 1);
    for (int i = 0; i < individualPlayScores.size(); i++) {
      numOfCombinations.set(0, 1);
      for (int j = 1; j < finalScore + 1; j++) {
        int withoutThisPlay = i > 0 ? numOfCombinations.get(j) : 0;
        int withThisPlay = j >= i ? numOfCombinations.get(j-i) : 0;
        numOfCombinations.set(j, withThisPlay + withoutThisPlay);
      }
    }
    return numOfCombinations.get(finalScore);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
