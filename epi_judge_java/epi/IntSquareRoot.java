package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
  long left = 0, right = k;
  while (left <= right) {
    long mid  = left + (right - left) / 2;
    long midsquared = mid * mid;
    if (midsquared == k) {
      return (int)mid;
    }
    else if (midsquared > k) {
      right = mid - 1;
    }
    else {
      left = mid + 1;
    }
  }
  return (int)left -1;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
