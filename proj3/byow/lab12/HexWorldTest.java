package byow.lab12;

import org.junit.Test;

import static byow.lab12.HexWorld.*;
import static org.junit.Assert.assertEquals;

public class HexWorldTest {

    @Test
    public void testHexRowWidth() {
        assertEquals(2,  hexRowWidth(2, 3));
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(8, hexRowWidth(4, 2));
        assertEquals(10, hexRowWidth(4, 3));
        assertEquals(10, hexRowWidth(4, 4));
    }
    @Test
    public void testHexOffset() {
        assertEquals(0,  hexRowOffset(2, 0));
        assertEquals(-1,  hexRowOffset(2, 1));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(0, hexRowOffset(4, 7));
        assertEquals(-1, hexRowOffset(4, 6));
        assertEquals(-3, hexRowOffset(4, 3));
        assertEquals(-3, hexRowOffset(4, 4));
    }
}

