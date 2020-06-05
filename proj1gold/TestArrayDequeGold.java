import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {

    @Test
    public void testArratDeque2() {
        ArrayDequeSolution<Integer> expect = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> input = new StudentArrayDeque<>();
        int random = StdRandom.uniform(100);
        expect.addFirst(random);
        input.addFirst(random);
        assertEquals("addFirst("+random+")", expect.get(0), input.get(0));
        System.out.println("addFirst("+random+")");

        random = StdRandom.uniform(100);
        expect.addLast(random);
        input.addLast(random);
        assertEquals("addLast("+random+")", expect.get(1), input.get(1));
        System.out.println("addLast("+random+")");

        int actual = expect.removeFirst();
        int expected = expect.removeFirst();
        assertEquals("removeFirst()", actual, expected);
        System.out.println("removeFirst()");

        actual = expect.removeLast();
        expected = input.removeLast();
        assertEquals("removeLast()", actual, expected);
        System.out.println("removeLast()");
    }


}
