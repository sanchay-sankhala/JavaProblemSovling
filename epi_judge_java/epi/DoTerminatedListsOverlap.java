package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // TODO - you fill in here.
    int n0 = length(l0), n1 = length(l1);
    ListNode<Integer> i0 = l0, i1 = l1;
    if (n1 > n0) {
      i1 = advanceListBy(l1, n1 -n0);
    }
    else if (n1 < n0) {
      i0 = advanceListBy(l0, n0 - n1);
    }
    while (i0 != null && i1 != null && i0 != i1) {
      i0 = i0.next;
      i1 = i1.next;
    }

    return i0 == i1 ? i1 : null;
  }
  public static int length(ListNode<Integer> l) {
    int n = 0;
    ListNode<Integer> iter = l;
    while (iter != null) {
      iter = iter.next;
      n++;
    }
    return n;
  }

  public static ListNode<Integer> advanceListBy(ListNode<Integer> l, int k) {
    while (k-- > 0) {
      l = l.next;
    }
    return l;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
