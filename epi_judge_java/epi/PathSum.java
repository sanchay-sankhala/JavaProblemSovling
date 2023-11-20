package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    List<List<Integer>> pathLists = new ArrayList<>();
    boolean result = hasPathSumHelper(tree, 0, remainingWeight, pathLists, new ArrayList<>());
    System.out.println(pathLists);
    return result;
  }

  public static class Status {
    int sum;
    boolean hasPathSum;

    public Status(int sum, boolean hasPathSum) {
      this.sum = sum;
      this.hasPathSum = hasPathSum;
    }
  }

  public static boolean hasPathSumHelper(BinaryTreeNode<Integer> tree, int partialSum, int target, List<List<Integer>> pathLists, List<Integer> path) {
    if (tree == null) {
      return false;
    }
    path.add(tree.data);
    partialSum += tree.data;
    if (tree.left == null && tree.right == null) {
      if (partialSum == target) {
        pathLists.add(new ArrayList<>(path));
      }
      path.remove(path.size() - 1);
      return partialSum == target;
    }

    boolean resultl = hasPathSumHelper(tree.left, partialSum, target, pathLists, path);
    boolean resultr =  hasPathSumHelper(tree.right, partialSum, target, pathLists, path);
    path.remove(path.size() -1);
    return resultl || resultr;
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
