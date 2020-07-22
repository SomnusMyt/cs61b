package bearmaps;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void TestNearest() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        assertEquals(new Point(1,5), nn.nearest(0, 7));
    }

    @Test
    public void TestRandomNearest() {
        List<Point> l = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            l.add(new Point(random.nextInt(1000), random.nextInt(1000)));
        }
        KDTree nn = new KDTree(l);
        NaivePointSet nps = new NaivePointSet(l);
        for (int i = 0; i < 100; i++) {
            Point input = new Point(random.nextInt(1000), random.nextInt(1000));
            assertEquals(nn.nearest(input.getX(), input.getY()), nps.nearest(input.getX(), input.getY()));
        }
    }

    @Test
    public void compareTimingOfNaiveVsKDTreeLikeTheSpec() {
        List<Point> randomPoints = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            randomPoints.add(new Point(random.nextDouble(), random.nextDouble()));
        }
        KDTree kd = new KDTree(randomPoints);
        NaivePointSet nps = new NaivePointSet(randomPoints);
        List<Point> queryPoints = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            queryPoints.add(new Point(random.nextDouble(), random.nextDouble()));
        }
        long start = System.currentTimeMillis();
        for (Point p : queryPoints) {
            nps.nearest(p.getX(), p.getY());
        }
        long end = System.currentTimeMillis();
        System.out.println("Naive 10000 queries on 100000 points: " + (end - start) / 1000.0 + "seconds.");

        start = System.currentTimeMillis();
        for (Point p : queryPoints) {
            kd.nearest(p.getX(), p.getY());
        }
        end = System.currentTimeMillis();
        System.out.println("KDTree 10000 queries on 100000 points: " + (end - start) / 1000.0 + "seconds.");
    }
}
