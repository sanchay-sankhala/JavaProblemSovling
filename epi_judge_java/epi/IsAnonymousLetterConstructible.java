package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    // TODO - you fill in here.
    HashMap<Character, Integer> charFreq = new HashMap<>();
    for (char c : letterText.toCharArray()) {
      int freq = charFreq.get(c) != null ? charFreq.get(c) + 1 : 1;
      charFreq.put(c, freq);
    }
    for (char c : magazineText.toCharArray()) {
      if (charFreq.containsKey(c)) {
        if (charFreq.get(c) > 1) {
          charFreq.put(c, charFreq.get(c) - 1);
        }
        else {
          charFreq.remove(c);
        }
      }
    }
    return charFreq.size() == 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
