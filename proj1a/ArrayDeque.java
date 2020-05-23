public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        size = 0;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        int i = plusOne(nextFirst);
        while (i != nextLast) {
            items[i] = (T) other.items[i];
            i = plusOne(i);
        }

    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;

    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int t = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[t];
            t = plusOne(t);
        }
        items = a;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    /** Inserts X into the front of the list.*/
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = x;
        size++;
        nextLast = plusOne(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque() {
        int i = nextFirst++;
        while (i != nextLast) {
            System.out.print((String) items[i] + " ");
            i = plusOne(i);
        }
        System.out.print("\n");
    }

    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        if (i > size) {
            return null;
        }
        i = (plusOne(nextFirst) + i) % items.length;
        return items[i];
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T res = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length >= 16 && size < items.length * 0.25) {
            resize(size / 2);
        }
        return res;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T res = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length >= 16 && size < items.length * 0.25) {
            resize(size / 2);
        }
        return res;
    }
    public static void main(String[] args){
    }
}
