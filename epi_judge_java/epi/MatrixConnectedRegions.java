package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class MatrixConnectedRegions {
  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    boolean color = image.get(x).get(y);
    image.get(x).set(y, !color);
    final int[][]SHIFT = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    for (int[] s : SHIFT) {
      int x1 = x + s[0];
      int y1 = y + s[1];
      if(isInSameRegion(x1, y1, color, image)) {
        flipColor(x1, y1, image);
      }
    }
  }

  public static boolean isInSameRegion(int x, int y, boolean color, List<List<Boolean>> image) {
    return x >= 0 && x < image.size() && y >= 0 && y < image.get(x).size() && color == image.get(x).get(y);
  }
  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
