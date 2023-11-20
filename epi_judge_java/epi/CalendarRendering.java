package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint implements Comparable<Endpoint> {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

    @Override
    public int compareTo(Endpoint e) {
      if (this.time < e.time) {
        return -1;
      }
      else if (this.time == e.time) {
        return this.isStart ? -1 : 1;
      }
      else {
        return 1;
      }
    }
    }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    List<Endpoint> endpoints = new ArrayList<>();
    for (Event e : A) {
      endpoints.add(new Endpoint(e.start, true));
      endpoints.add(new Endpoint(e.finish, false));
    }
    Collections.sort(endpoints);
    int maxSimultaneous = 0, simultaneous = 0;
    for (Endpoint endpoint : endpoints) {
      if (endpoint.isStart) {
        ++simultaneous;
      }
      else {
        --simultaneous ;
      }
      maxSimultaneous = Math.max(simultaneous, maxSimultaneous);
    }
    return maxSimultaneous;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
