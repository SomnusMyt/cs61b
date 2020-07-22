package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayHeapMinPQTest {
    @Test
    public void testbasic() {
        ArrayHeapMinPQ<Integer> AHMPQ = new ArrayHeapMinPQ<>();
        AHMPQ.add(2, 2);
        AHMPQ.add(1, 1);
        AHMPQ.add(3, 3);
        assertTrue(AHMPQ.contains(1));
        assertTrue(AHMPQ.contains(2));
        assertTrue(AHMPQ.contains(3));
        assertEquals(3, AHMPQ.size());
        Integer i = 1;
        Integer ii = 2;
        Integer iii = 3;
        AHMPQ.changePriority(iii, 4);
        assertEquals(i, AHMPQ.getSmallest());
        assertEquals(i, AHMPQ.removeSmallest());
        assertEquals(ii, AHMPQ.removeSmallest());
        assertEquals(iii, AHMPQ.removeSmallest());
        assertEquals(0, AHMPQ.size());
    }


    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0);
        minHeap.add(6, 3);
        minHeap.changePriority(5, 5);
        minHeap.changePriority(6, 0);
        assertTrue(minHeap.getSmallest() == 6);
    }

}
