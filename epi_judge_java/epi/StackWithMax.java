package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
public class StackWithMax {

  public static class Stack {
    static Deque<Integer> stack = new LinkedList<>();
    static Deque<Integer> max =  new LinkedList<>();
    public boolean empty() {
      // TODO - you fill in here.
      return stack.peekFirst() == null;
    }
    public Integer max() {
      // TODO - you fill in here.
      return max.peekFirst();
    }
    public Integer pop() {
      // TODO - you fill in here.
      if (stack.peekFirst().equals(max.peekFirst())) {
        max.removeFirst();
      }
      return stack.removeFirst();
    }
    public void push(Integer x) {
      // TODO - you fill in here.
      stack.addFirst(x);
      if (max.peekFirst() == null || stack.peekFirst() >= max.peekFirst()) {
        max.addFirst(x);
      }
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
