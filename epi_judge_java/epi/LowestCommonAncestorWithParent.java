package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    // TODO - you fill in here.
    int h0 = getHeight(node0);
    int h1 = getHeight(node1);

    return h0 > h1 ? lcaHelper(node0, node1, h0 - h1) : lcaHelper(node1, node0, h1 - h0);
  }

  public static BinaryTree<Integer> lcaHelper(BinaryTree<Integer> x, BinaryTree<Integer> y, int h) {
    for (int i = 0 ; i < h; i++) {
      x = x.parent;
    }
    while (x != y) {
      x = x.parent;
      y = y.parent;
    }
    return x;
  }
  public static int getHeight(BinaryTree<Integer> node) {
    int h = 0;
    while (node.parent != null) {
      node = node.parent;
      h++;
    }
    return h;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
