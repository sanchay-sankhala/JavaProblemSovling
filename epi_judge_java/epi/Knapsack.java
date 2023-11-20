package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    int[][] arr = new int[items.size()][capacity + 1];
    for (int i = 0; i < items.size(); i++) {
      Arrays.fill(arr[i], -1);
    }
    return maxWeightToCapacity(items, capacity, items.size() - 1, arr);
  }
  public static int maxWeightToCapacity(List<Item> items, int remainingCapacity, int toBeSelected, int[][] dp ) {
    if (toBeSelected < 0) {
      return 0;
    }
    if (dp[toBeSelected][remainingCapacity] ==  -1) {
      Item current = items.get(toBeSelected);
      int maxWithCurrent = 0;
      if (remainingCapacity >= current.weight) {
        maxWithCurrent = current.value +  maxWeightToCapacity(items, remainingCapacity - current.weight, toBeSelected - 1, dp);
      }
      int maxWithoutCurrent = maxWeightToCapacity(items, remainingCapacity, toBeSelected - 1, dp);
      dp[toBeSelected][remainingCapacity] = Math.max(maxWithCurrent, maxWithoutCurrent);
    }
    return dp[toBeSelected][remainingCapacity];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
