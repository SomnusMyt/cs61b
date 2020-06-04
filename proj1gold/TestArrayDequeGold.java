import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque () {
        StudentArrayDeque<Integer> input = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expect = new ArrayDequeSolution<>();

        int random = StdRandom.uniform(10);
        input.addFirst(random);
        expect.addFirst(random);
        assertEquals("addFirst(" + random + ")", input.get(0), expect.get(0));
        System.out.println("addFirst(" + random + ")");

        random = StdRandom.uniform(10);
        input.addLast(random);
        expect.addLast(random);
        assertEquals("addLast(" + random + ")", input.get(1), expect.get(1));
        System.out.println("addLast(" + random + ")");

        int actual = input.removeFirst();
        int expected = expect.removeFirst();
        assertEquals("removeFirst()", actual, expected);
        System.out.println("removeFirst()");

        actual = input.removeLast();
        expected = expect.removeLast();
        assertEquals("removeLast()", actual, expected);
        System.out.println("removeLast()");
    }

}
