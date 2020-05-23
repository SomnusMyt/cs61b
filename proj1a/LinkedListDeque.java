public class LinkedListDeque<T> {
    private Node sentinel;
    private  int size;

    public class Node {
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

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = other.size;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        size++;
        Node t = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = t;
        sentinel.next = t;
    }

    public void addLast(T item) {
        size++;
        Node t = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = t;
        sentinel.prev = t;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print((String) p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T t = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next =  sentinel.next.next;
        return t;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T t = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return t;
    }

    public T get(int index) {
        if (index > size()) {
            return null;
        }
        Node p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

}
