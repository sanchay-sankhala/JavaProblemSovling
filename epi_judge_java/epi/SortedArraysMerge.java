package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge {

  public static class ArrayEntry {
    Integer val;
    int arrayId;

    public ArrayEntry(Integer val, int arrayId) {
      this.val = val;
      this.arrayId = arrayId;
    }
  }
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.
    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(), Comparator.comparingInt(o -> o.val));
    List<Iterator<Integer>> itrs = new ArrayList<>();
    for (List<Integer> list : sortedArrays) {
      itrs.add(list.iterator());
    }
    for (int i = 0; i < itrs.size(); i ++) {
      if (itrs.get(i).hasNext()) {
        minHeap.add(new ArrayEntry(itrs.get(i).next(), i));
      }
    }
    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      ArrayEntry a = minHeap.poll();
      result.add(a.val);
      if (itrs.get(a.arrayId).hasNext()) {
        minHeap.add(new ArrayEntry(itrs.get(a.arrayId).next(), a.arrayId));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
