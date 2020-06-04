import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque () {
        StudentArrayDeque<Integer> input = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expect = new ArrayDequeSolution<>();

        // addLast
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            input.addLast(random);
            expect.addLast(random);
        }
        for (int i=0; i<10; i++) {
            int actual = input.get(i);
            int expected = expect.get(i);
            assertEquals("addLast()" + expected + "!",
                    expected, actual);
        }

        // addFirst
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            input.addFirst(random);
            expect.addFirst(random);
        }
        for (int i=0; i<10; i++) {
            int actual = input.get(i);
            int expected = expect.get(i);
            assertEquals("addFirst()" + expected + "!",
                    expected, actual);
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            actualList.add(input.removeFirst());
            expectedList.add(expect.removeFirst());
        }
        for (int i=0; i<10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("removeFirst()" + expected + "!",
                    expected, actual);
        }


        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i=0; i<5; i++) {
            actualList.add(input.removeLast());
            expectedList.add(expect.removeLast());
        }
        int actual = input.size();
        int expected = expect.size();
        assertEquals("size()",
                expected, actual);
        for (int i=0; i<5; i++) {
            assertEquals("removeLast()",
                    expectedList.get(i), actualList.get(i));
        }
    }

}
