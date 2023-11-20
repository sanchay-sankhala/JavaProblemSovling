package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // TODO - you fill in here.
    ListNode<Integer> itr1 = L1, itr2 = L2;
    ListNode<Integer> dummyHead = new ListNode<>(0, null);
    ListNode<Integer> current = dummyHead;
    while (itr1 != null && itr2 != null) {
      if (itr1.data <= itr2.data) {
        current.next = itr1;
        itr1 = itr1.next;
      }
      else {
        current.next = itr2;
        itr2 = itr2.next;
      }
      current = current.next;
    }
    current.next = itr1 == null ? itr2 : itr1;
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
