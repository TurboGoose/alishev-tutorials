package collections;

public class MyLinkedList {
    private Node head = null;
    private int size = 0;

    public void add(int value) {
        Node newLast = new Node(value);
        size++;
        if (head == null) {
            head = newLast;
            return;
        }
        Node curLast = head;
        while (curLast.next != null) {
            curLast = curLast.next;
        }
        curLast.next = newLast;
    }

    public void insert(int index, int value) {
        checkIndex(index, true);
        size++;
        Node toInsert = new Node(value);
        if (head == null) {
            head = toInsert;
            return;
        }
        Node before = null;
        Node after = head;
        for (int i = 0; i < index; i++) {
            before = after;
            after = after.next;
        }
        if (before == null) {
            head.next = null;
            head = toInsert;
        } else {
            before.next = toInsert;
        }
        toInsert.next = after;
    }

    public void remove(int index) {
        checkIndex(index);
        size--;
        Node before = null;
        Node toDelete = head;
        for (int i = 0; i < index; i++) {
            before = toDelete;
            toDelete = toDelete.next;
        }
        if (before == null) {
            head = toDelete.next;
        } else {
            before.next = toDelete.next;
        }
        toDelete.next = null;
    }

    public int get(int index) {
        checkIndex(index);
        Node toGet = head;
        for (int i = 0; i < index; i++) {
            toGet = toGet.next;
        }
        return toGet.value;
    }

    private void checkIndex(int index) {
        checkIndex(index, false);
    }

    private void checkIndex(int index, boolean inclusive) {
        if (index < 0 || (inclusive ? index > size : index >= size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getSize() {
        return size;
    }

    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
