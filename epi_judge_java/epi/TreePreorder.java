package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreePreorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean nodeProcessed;

    public NodeAndState(BinaryTreeNode<Integer> node, Boolean nodeProcessed) {
      this.node = node;
      this.nodeProcessed = nodeProcessed;
    }
  }

  @EpiTest(testDataFile = "tree_preorder.tsv")
  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
    BinaryTreeNode<Integer> cur = tree;
    while (cur != null || !s.isEmpty()) {
      if (cur != null) {
        result.add(cur.data);
        s.push(cur.right);
        cur = cur.left;
      }
      else {
        cur = s.pop();
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
