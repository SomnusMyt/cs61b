import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque () {
        StudentArrayDeque<Integer> input = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expect = new ArrayDequeSolution<>();

        //addLast
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(10);
            input.addLast(random);
            expect.addLast(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = input.get(i);
            int expected = expect.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        //addFirst
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(10);
            input.addFirst(random);
            expect.addFirst(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = input.get(i);
            int expected = expect.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        //removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualList.add(input.removeFirst());
            expectedList.add(expect.removeFirst());
        }
        for (int i = 0; i < 10; i++) {
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actualList.get(i)
                            + " not equal to " +  expectedList.get(i) + "!",
                    expectedList.get(i), actualList.get(i));
        }

        actualList.clear();
        expectedList.clear();
        for (int i = 0; i < 10; i++) {
            actualList.add(input.removeLast());
            expectedList.add(expect.removeLast());
        }
        int actualSize = input.size();
        int expectSize = expect.size();
        assertEquals("Oh noooo!\nThis is bad in Size():\n   Random number " + actualSize
                        + " not equal to " +  expectSize + "!",
                expectSize, actualSize);
        for (int i = 0; i < 10; i++) {
            assertEquals("Oh noooo!\nThis is bad in removeLast():\n   Random number " + actualList.get(i)
                            + " not equal to " +  expectedList.get(i) + "!",
                    expectedList.get(i), actualList.get(i));
        }


    }
}
