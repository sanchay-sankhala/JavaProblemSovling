package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {
    // TODO - you fill in here.
    ListNode<Integer> c1 = IsListCyclic.hasCycle(l0);
    ListNode<Integer> c2 = IsListCyclic.hasCycle(l1);
    ListNode<Integer> i1 = l0, i2 = l1;
    if (c1 == c2) {
      int n1 = indexOfNode(l0, c1);
      int n2 = indexOfNode(l1, c2);
      if (n1 > n2) {
        i1 = DoTerminatedListsOverlap.advanceListBy(i1, n1 - n2);
      }
      else {
        i2 = DoTerminatedListsOverlap.advanceListBy(i2, n2 - n1);
      }
      while (i1 != null && i2 != null && i1 != i2) {
        i1 = i1.next;
        i2 = i2.next;
      }
      return i1 == i2 ? i1 : null;
    }
    else if (c1 == null && c2 == null) {
      return DoTerminatedListsOverlap.overlappingNoCycleLists(l0, l1);
    }

    else if (c1 != null && c2 != null) {
      ListNode<Integer> itr = c1;
      while (itr != c2) {
        itr = itr.next;
        if (itr == c1) {
          return null;
        }
      }
      int n1 = indexOfNode(l0, c1);
      int n2 = indexOfNode(l1, c2);
      return n1 < n2 ? c1 : c2;
    }

    return null;
  }
  public static int indexOfNode(ListNode<Integer> l , ListNode<Integer> k) {
    int n = 0;
    ListNode<Integer> itr = l;
    while (itr != k) {
      n++;
      itr = itr.next;
    }
    return n;
  }
  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
