package bearmaps.proj2ab;


import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> hm;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        hm = new HashMap<>();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        hm.put(item, size() - 1);
        up(size() - 1);
    }

    private int parent(int p) {
        return (p - 1) / 2;
    }

    private int leftChild(int p) {
        return p * 2 + 1;
    }

    private int rightChild(int p) {
        return p * 2 + 2;
    }

    private void up(int k) {
        while (k != 0) {
            if (items.get(k).compareTo(items.get(parent(k))) == -1) {
                hm.put(items.get(k).item, parent(k));
                hm.put(items.get(parent(k)).item, k);
                PriorityNode temp = items.get(k);
                items.set(k, items.get(parent(k)));
                items.set(parent(k), temp);
                k = parent(k);
            } else {
                k = parent(k);
            }
        }
    }

    public boolean contains(T item) {
        return hm.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        return items.get(0).item;
    }

    @Override
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        hm.remove(items.get(0).item);
        T result = items.get(0).item;
        items.set(0, items.get(size() - 1));
        items.remove(size() - 1);
        down(0);
        return result;
    }

    private void down(int k) {
        while (leftChild(k) < size()) {
            int child = leftChild(k);
            if (child + 1 < size()) {
                if (items.get(child).compareTo(items.get(child + 1)) == 1) {
                    child++;
                }
            }
            if (items.get(k).compareTo(items.get(child)) == -1) {
                break;
            }
            PriorityNode temp = items.get(k);
            items.set(k, items.get(child));
            items.set(child, temp);
            k = child;
        }

    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return items.size();
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new IllegalArgumentException();
        }
        if (items.get(hm.get(item)).priority > priority) {
            items.get(hm.get(item)).priority = priority;
            up(hm.get(item));
        } else {
            items.get(hm.get(item)).priority = priority;
            down(hm.get(item));
        }

    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private final T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.priority, other.priority);
        }
    }
    public static void main(String[] args) {
        /*
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ();
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 100000; i += 1) {
            pq.add(i, i);
        }
        for (int i = 0; i < 100000; i += 1) {
            pq.changePriority(i, i+1);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");

        NaiveMinPQ<Integer> npq = new NaiveMinPQ();
        sw = new Stopwatch();
        for (int i = 0; i < 100000; i += 1) {
            npq.add(i, i);
        }
        for (int i = 0; i < 100000; i += 1) {
            npq.changePriority(i, i+1);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
         */
    }

}
