public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private  int size;

    private class Node {
        private  T item;
        private  Node prev;
        private  Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node t = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = t;
        sentinel.next = t;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node t = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = t;
        sentinel.prev = t;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T t = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next =  sentinel.next.next;
        return t;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T t = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return t;
    }

    @Override
    public T get(int index) {
        if (index > size()) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.item;
    }

    private T getRecursive(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

}
