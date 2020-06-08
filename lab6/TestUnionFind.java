import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    UnionFind u = new UnionFind(7);
    UnionFind uf = new UnionFind(7);
    @Test
    public void testBasic() {
        u.union(4,0);
        u.union(2,1);
        u.union(1,0);
        assertEquals(0,u.parent(4));
        assertEquals(0,u.parent(1));
        assertEquals(1,u.parent(2));
        assertEquals(0,u.find(2));
        assertEquals(4,u.sizeOf(0));
        assertEquals(4,u.sizeOf(1));
        assertEquals(4,u.sizeOf(2));
        assertEquals(4,u.sizeOf(4));

        u.union(3,5);
        assertEquals(true,u.connected(3,5));
        assertEquals(false,u.connected(3,6));
    }

    @Test
    public void testBasic1() {
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 2);
        assertEquals(1, uf.parent(0));

        // assertTrue(uf.connected(0, 2));
        //uf.union(0, 2);
        assertEquals(true, uf.connected(0,2));
        assertEquals(3, uf.parent(0)); // Path-compression

        assertEquals(4, uf.sizeOf(0));
        assertEquals(4, uf.sizeOf(1));
        assertEquals(4, uf.sizeOf(2));
        assertEquals(4, uf.sizeOf(3));
        assertTrue(uf.connected(1, 3));

        assertEquals(3, uf.find(0));
}}
