package br.com.mangarosa.collections;

public class ListaEncadeada {

    private static class Node {
        Object value;
        Node next;

        Node(Object value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;

    public void append(Object value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void insertAt(int position, Object value) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posição inválida: " + position);
        }
        Node newNode = new Node(value);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node previous = head;
            for (int i = 0; i < position - 1; i++) {
                previous = previous.next;
            }
            newNode.next = previous.next;
            previous.next = newNode;
        }
        size++;
    }

    public void addAll(ListaEncadeada list) {
        Node current = list.head;
        while (current != null) {
            this.append(current.value);
            current = current.next;
        }
    }

    public boolean remove(int position) {
        if (position < 0 || position >= size) {
            return false;
        }

        if (position == 0) {
            head = head.next;
        } else {
            Node previous = head;
            for (int i = 0; i < position - 1; i++) {
                previous = previous.next;
            }
            previous.next = previous.next.next;
        }
        size--;
        return true;
    }

    public boolean clear() {
        head = null;
        size = 0;
        return true;
    }

    public Object get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posição inválida: " + position);
        }
        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int indexOf(Object value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if ((value == null && current.value == null) ||
                    (value != null && value.equals(current.value))) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }
}
