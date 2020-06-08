package es.datastructur.synthesizer;

import java.util.concurrent.RecursiveTask;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);

}
