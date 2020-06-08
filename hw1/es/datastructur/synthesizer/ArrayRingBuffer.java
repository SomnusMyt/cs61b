package es.datastructur.synthesizer;

import java.net.PortUnreachableException;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
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


    @Override
    public int capacity() {
        return rb.length;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
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
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

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
}

