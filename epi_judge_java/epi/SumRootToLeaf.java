package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    return findSum(tree, 0);
  }

  public static int findSum(BinaryTreeNode<Integer> tree, int s) {
    if (tree == null) {
      return 0;
    }
    s = s * 2 + tree.data;
    if (tree.left == null && tree.right == null) {
      return s;
    }
    s = findSum(tree.left, s) + findSum(tree.right, s);
    return s;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
