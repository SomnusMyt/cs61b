import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testisSameNumber() {
        Integer a = 128;
        Integer b = 128;
        Integer c = 1000;
        boolean res1 = Flik.isSameNumber(a,b);
        boolean res2 = Flik.isSameNumber(a,c);

        assertTrue(res1);
        assertFalse(res2);
    }
}
