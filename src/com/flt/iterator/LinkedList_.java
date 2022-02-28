package com.flt.iterator;

public class LinkedList_ implements Collection_ {
    private Node head;
    private Node tail;
    private int size = 0;

    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Node(o);
            tail = head;
        }
        tail.next = new Node(o);
        tail = tail.next;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        private Node next;
        private Object value;

        public Node(Object value) {
            this.value = value;
        }
    }

    @Override
    public Iterator_ iterator() {
        return new LinkedListIterator_();
    }

    private class LinkedListIterator_ implements Iterator_ {
        private Node curNode = head;

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public Object next() {
            Node o = this.curNode;
            curNode = curNode.next;
            return o.value;
        }
    }
}
