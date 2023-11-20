package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<List<Integer>> result = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> currentDepth = new LinkedList<>();
    currentDepth.add(tree);
    while (!currentDepth.isEmpty()) {
      Deque<BinaryTreeNode<Integer>> nextDepth = new LinkedList<>();
      List<Integer> thisLevel = new ArrayList<>();
      while (!currentDepth.isEmpty()) {
        BinaryTreeNode<Integer> current = currentDepth.poll();
        if (current != null) {
          thisLevel.add(current.data);
          nextDepth.add(current.left);
          nextDepth.add(current.right);
        }
      }
      if (!thisLevel.isEmpty()) {
        result.add(thisLevel);
      }
      currentDepth = nextDepth;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
