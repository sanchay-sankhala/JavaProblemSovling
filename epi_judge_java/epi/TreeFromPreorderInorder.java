package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    if (preorder.size() == 0) {
      return null;
    }
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(0));
    int leftSize = inorder.indexOf(preorder.get(0));
    if (leftSize > 0) {
      root.left = binaryTreeFromPreorderInorder(preorder.subList(1, 1 + leftSize), inorder.subList(0, leftSize));
    }
    else {
      root.left = null;
    }
    if (leftSize + 1 < preorder.size()) {
      root.right = binaryTreeFromPreorderInorder(preorder.subList(1 + leftSize, preorder.size()), inorder.subList(1 + leftSize, inorder.size()));
    }
    else {
      root.right = null;
    }
    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
