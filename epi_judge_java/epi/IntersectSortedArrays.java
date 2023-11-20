package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // TODO - you fill in here.
    List<Integer> result = new ArrayList<>();
    int x = 0, y = 0;
    while (x < A.size() && y < B.size()) {
      if (A.get(x) == B.get(y) && (x == 0 || A.get(x) != A.get(x-1))){
        result.add(A.get(x));
        ++x;
        ++y;
      }
      else if (A.get(x) < B.get(y)) {
        ++x;
      }
      else {
        ++y;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
