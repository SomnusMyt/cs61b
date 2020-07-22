import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private int count = 0;

    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        PriorityQueue<Flight> startTimePQ = new PriorityQueue(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.startTime, o2.startTime);
            }
        });
        PriorityQueue<Flight> endTimePQ = new PriorityQueue(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.endTime, o2.endTime);
            }
        });

        startTimePQ.addAll(flights);
        endTimePQ.addAll(flights);

        int tally = 0;
        while (startTimePQ.size() != 0) {
            if (startTimePQ.peek().startTime() <= endTimePQ.peek().endTime()) {
                tally += startTimePQ.poll().passengers;
                count = count > tally ? count : tally;
            }
            else {
                tally -= endTimePQ.poll().passengers;
            }
        }
    }

    public int solve() {
        /* FIX ME */
        return count;
    }

}
