package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return isTreeBst(tree, Long.MIN_VALUE, Long.MAX_VALUE);
  }
  public static boolean isTreeBst(BinaryTreeNode<Integer> tree, long min, long max) {
    // TODO - you fill in here.
    if (tree == null ) {
      return true;
    }
    if (tree.data < min || tree.data > max) {
      return false;
    }
    boolean left = isTreeBst(tree.left, min, tree.data);
    boolean right = isTreeBst(tree.right, tree.data, max);
    return left && right;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
