package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    // TODO - you fill in here.
    ListNode<Integer> slow = L, fast = L;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode<Integer> reversed = reverseList(fast==null ? slow : slow.next);
    ListNode<Integer> iter = L, riter = reversed;
    while (riter != null) {
      if(!iter.data.equals(riter.data)) {
        return false;
      }
      iter = iter.next;
      riter = riter.next;
    }
    return true;
  }

  public static ListNode<Integer> reverseList(ListNode<Integer> l) {
    ListNode<Integer> head = l, iter = l;
    while (iter != null && iter.next != null) {
      ListNode<Integer> temp = iter.next;
      iter.next = temp.next;
      temp.next = head;
      head = temp;
    }
    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
