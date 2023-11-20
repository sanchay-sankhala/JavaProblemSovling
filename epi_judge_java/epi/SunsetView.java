package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.
    Deque<Integer> l = new LinkedList<>();
    Deque<Integer> p = new LinkedList<>();
    int pos = 0;
    while (sequence.hasNext()) {
      int curr = sequence.next();
      while (!l.isEmpty() && l.peek() <= curr) {
        l.pop();
        p.pop();
      }
      if (l.isEmpty() || l.peek() > curr) {
        l.push(curr);
        p.push(pos);
      }
      pos++;
    }

    return new ArrayList<>(p);
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
