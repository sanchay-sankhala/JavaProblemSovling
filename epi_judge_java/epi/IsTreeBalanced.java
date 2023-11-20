package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  public static class BalancedStatusWithHeight {
    int height;
    boolean balanced;
    public BalancedStatusWithHeight(int height, boolean balanced) {
      this.height = height;
      this.balanced = balanced;
    }
  }
  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    return checkBalancedTree(tree).balanced;
  }

  public static BalancedStatusWithHeight checkBalancedTree(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalancedStatusWithHeight(-1, true);
    }
    BalancedStatusWithHeight left = checkBalancedTree(tree.left);
    if (!left.balanced) {
      return left;
    }
    BalancedStatusWithHeight right = checkBalancedTree(tree.right);
    if (!right.balanced) {
      return right;
    }
    boolean balance = Math.abs(left.height - right.height) <= 1;
    int height = Math.max(left.height, right.height) + 1;
    return new BalancedStatusWithHeight(height, balance);

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
