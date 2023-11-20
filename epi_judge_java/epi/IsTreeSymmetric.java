package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return tree==null || checkSymmetry(tree.left, tree.right);
  }
  public static boolean checkSymmetry(BinaryTreeNode<Integer> l, BinaryTreeNode<Integer> r) {
    if (l == null && r == null) {
      return true;
    }
    else if(l != null && r !=null){
      return l.data == r.data && checkSymmetry(l.left, r.right) && checkSymmetry(r.left, l.right);
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
