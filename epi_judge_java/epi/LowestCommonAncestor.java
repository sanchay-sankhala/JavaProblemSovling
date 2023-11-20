package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    // TODO - you fill in here.

    return lcaH(tree,node0, node1).r;
  }
  public static class Status {
    int n;
    BinaryTreeNode<Integer> r;
    public Status(int n, BinaryTreeNode<Integer> r) {
      this.n = n;
      this.r = r;
    }
  }
  public static Status lcaH(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> x, BinaryTreeNode<Integer> y) {
    if (tree == null) {
      return new Status(0, null);
    }
    Status l = lcaH(tree.left, x, y);
    if (l.n == 2) {
      return l;
    }

    Status r = lcaH(tree.right, x, y);
    if (r.n == 2) {
      return r;
    }
    int n = l.n + r.n + (tree == x ? 1 : 0) + (tree == y ? 1:0);
    return new Status(n, n == 2 ? tree : null);
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> lca(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
