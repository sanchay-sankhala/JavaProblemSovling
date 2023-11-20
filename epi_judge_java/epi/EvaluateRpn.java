package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.
    Deque<Integer> numbers = new LinkedList<>();
    for (String s : expression.split(",")) {
      if (s.length() == 1 && "+-/*".contains(s)) {
        int y = numbers.removeFirst();
        int x = numbers.removeFirst();
        switch (s.charAt(0)) {
          case '+':
            numbers.addFirst(x + y);
            break;
          case '-':
            numbers.addFirst(x - y);
            break;
          case '*':
            numbers.addFirst(x*y);
            break;
          default:
            numbers.addFirst(x/y);
            break;
        }
      }
      else {
        numbers.addFirst(Integer.parseInt(s));
      }
    }
    return numbers.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
