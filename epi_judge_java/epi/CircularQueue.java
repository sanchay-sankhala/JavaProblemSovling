package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class CircularQueue {

  public static class Queue {

    static final int SCALE = 2;
    Integer[] arr;
    int head = 0, tail = 0, size = 0;
    public Queue(int capacity) {
      arr = new Integer[capacity];
    }
    public void enqueue(Integer x) {
      // TODO - you fill in here.
      if (arr.length == size) {
        Collections.rotate(Arrays.asList(arr), -head);
        head = 0;
        tail = size;
        arr = Arrays.copyOf(arr, size * SCALE);
      }
      arr[tail] = x;
      tail = (tail + 1) % arr.length;
      ++size;
    }
    public Integer dequeue() {
      // TODO - you fill in here.
      if (size == 0) {
        throw new NoSuchElementException();
      }
      else {
        Integer ret = arr[head];
        head = (head + 1) % arr.length;
        --size;
        return ret;
      }
    }
    public int size() {
      // TODO - you fill in here.
      return size;
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
