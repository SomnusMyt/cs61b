import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return get(n.left, key);
        }
        else if (cmp > 0) {
            return get(n.right, key);
        }
        else {
            return n.value;
        }

    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        else {
            return n.size;
        }
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, value);
        }
        else if (cmp > 0) {
            n.right = put(n.right, key, value);
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    private Node select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, k);
    }

    private Node select(Node n, int k) {
        if (n == null) {
            return null;
        }
        int t = size(n.left);
        if (t > k) {
            return select(n.left, k);
        }
        else if (t < k) {
            return select(n.right, k - t - 1);
        }
        else {
            return n;
        }
    }
    @Override
    public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        for (int i = 0; i < size(); i++) {
            BSTSet.add(select(i).key);
        }
        return BSTSet;
    }

    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V val = get(key);
        root = remove(root, key);
        return val;
    }

    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        if (!get(key).equals(value)) {
            return null;
        }
        root = remove(root, key);
        return value;
    }

    private Node remove(Node n, K key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(n.left, key);
        }
        else if (cmp > 0) {
            n.right = remove(n.right, key);
        }
        else {
            if (n.right == null) {
                return n.left;
            }
            if (n.left == null) {
                return n.right;
            }
            Node temp = n;
            n = temp.right;
            while (n.left != null) {
                n = n.left;
            }
            n.right = removeNode(temp.right);
            n.left = temp.left;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private Node removeNode(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = removeNode(n.left);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }
    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node src) {
            while (src != null) {
                stack.push(src);
                src = src.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node n = stack.pop();
            if (n.right != null) {
                Node temp = n.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }

            return n.key;

        }
    }
    public static void main(String[] args) {
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        for (int i = 0; i < 10; i++) {
            bstMap.put("hi" + i, 1 + i);
        }
//        bstMap.printInOrder();
        Iterator<String> itr = bstMap.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
