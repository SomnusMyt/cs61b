import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        // addLast
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("addLast(" + random + ")",
                    expected, actual);
            System.out.println("addLast(" + random + ")");
        }


        // addFirst
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("addFirst(" + random + ")",
                    expected, actual);
            System.out.println("addFirst(" + random + ")");
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            actualList.add(ads.removeFirst());
            expectedList.add(sad.removeFirst());
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("removeFirst()",
                    expected, actual);
            System.out.println("removeFirst()");
        }

        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i=0; i<10; i++) {
            actualList.add(ads.removeLast());
            expectedList.add(sad.removeLast());
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("removeLast()",
                    expectedList.get(i), actualList.get(i));
            System.out.println("removeLast()");
        }


        }



}
