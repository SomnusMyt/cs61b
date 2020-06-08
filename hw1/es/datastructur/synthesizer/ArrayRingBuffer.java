package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

<<<<<<< HEAD
public class ArrayRingBuffer<T> implements BoundedQueue<T>{
=======
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
>>>>>>> ca83568314de95d735a6fae127bbbade9b8e6669
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
<<<<<<< HEAD
    private T[] rb;
=======
    private final T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }
>>>>>>> ca83568314de95d735a6fae127bbbade9b8e6669


    @Override
    public int capacity() {
        return rb.length;
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
<<<<<<< HEAD
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
=======
>>>>>>> ca83568314de95d735a6fae127bbbade9b8e6669
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T result = rb[first];
        first = (first + 1) % rb.length;
        fillCount--;
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
<<<<<<< HEAD
        // TODO: Return the first item. None of your instance variables should
        //       change.
=======
>>>>>>> ca83568314de95d735a6fae127bbbade9b8e6669
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

<<<<<<< HEAD
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
=======
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int count;
        private int current;

        public ArrayRingBufferIterator() {
            count = 0;
            current = first;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount();
        }

        @Override
        public T next() {
            T res = rb[current];
            current = (current + 1) % capacity();
            count++;
            return res;
        }
    }

    @Override
    public boolean equals(Object oo) {
        if (oo == this) {
            return true;
        }
        if (this.hashCode() == oo.hashCode()) {
            return true;
        }
        if (oo == null) {
            return false;
        }
        if (oo.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) oo;
        if (o.fillCount() != this.fillCount()) {
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> oIterator = o.iterator();
        while (thisIterator.hasNext() && oIterator.hasNext()) {
            if (thisIterator.next() != oIterator.next()) {
                return false;
            }
        }
        return true;
    }
>>>>>>> ca83568314de95d735a6fae127bbbade9b8e6669
}
    // TODO: Remove all comments that say TODO when you're done.
