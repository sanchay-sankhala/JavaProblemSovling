package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    // TODO - you fill in here.
    Random r = new Random(0);
    int left = 0, right = A.size() - 1;
    while (left <= right) {
      int pivot = r.nextInt(right - left + 1) + left;
      int newPivot = partitionAroundPivot(pivot, A, left, right);
      if (newPivot + 1 == k) {
        return A.get(newPivot);
      }
      else if (newPivot + 1 < k) {
        left = newPivot + 1;
      }
      else {
        right = newPivot -1;
      }
    }
    return 0;
  }

  public static int partitionAroundPivot(int pivotIndex, List<Integer> A, int left, int right) {
    Integer pivotValue = A.get(pivotIndex);
    int pivotIdx = left;
    Collections.swap(A, pivotIndex, right);
    for (int i = left; i < right; i++) {
      if (pivotValue.compareTo(A.get(i)) < 0) {
        Collections.swap(A, pivotIdx++, i);
      }
    }
    Collections.swap(A, pivotIdx, right);
    return pivotIdx;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
