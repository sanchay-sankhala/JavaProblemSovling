package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class StringTransformability {
  @EpiTest(testDataFile = "string_transformability.tsv")
  public static int transformString(Set<String> D, String s, String t) {
    Deque<StringWithDistance> queue = new LinkedList<>();
    queue.add(new StringWithDistance(s, 0));
    D.remove(s);
    while (!queue.isEmpty()) {
      StringWithDistance cur = queue.poll();
      char[] chars = cur.string.toCharArray();
      for (int i = 0; i < cur.string.length(); i++) {
        for (int j = 0 ; j < 26; j++) {
          char prev = chars[i];
          chars[i] = (char)('a' + j);
          String entry = new String(chars);
          if (D.contains(entry)) {
            if (entry.equals(t)) {
              return cur.distance + 1;
            }
            queue.add(new StringWithDistance(entry, cur.distance + 1));
            D.remove(entry);
          }
          chars[i] = prev;
        }
      }
    }
    return -1;
  }

  public static class StringWithDistance {
    public String string;
    public int distance;

    public StringWithDistance(String string, int distance) {
      this.string = string;
      this.distance = distance;
    }
  }

  public static boolean isAdjacent(String x, String y) {
    if (x.length() != y.length() || x.equals(y)) {
      return false;
    }
    int diff = 0;
    for (int i = 0; i < x.length(); i++) {
      if (x.charAt(i) != y.charAt(i)) {
        diff ++;
      }
      if (diff > 1) {
        return false;
      }
    }
    return diff == 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
