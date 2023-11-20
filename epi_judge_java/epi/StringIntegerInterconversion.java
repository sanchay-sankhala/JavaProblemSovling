package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    StringBuilder s = new StringBuilder();
    boolean isNegative = x < 0;
    do {
      s.append(Math.abs(x%10));
      x /= 10;
    } while (x != 0);
    if (isNegative)
      s.append('-');
    return s.reverse().toString();
  }
  public static int stringToInt(String s) {
    // TODO - you fill in here.
    int result = 0;
    for (int i = s.charAt(0) == '-' || s.charAt(0) == '+' ? 1 : 0; i < s.length(); i++) {
      int digit = s.charAt(i) - '0';
      result = result*10 + digit;
    }
    return s.charAt(0) == '-' ? -result : result;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
