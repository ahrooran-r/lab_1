package uom.cp.impl;

import uom.cp.IList;

/**
 * This is a serial implementation of linked list
 * <p>
 * {@link SerialLinkedList#head} -> holds initial node
 */
public class SerialLinkedList implements IList {

    // head of list
    private Node head;

    @Override
    public boolean insert(int data) {

        Node newNode = new Node(data);

        // if head is null -> insert new node into head
        if (head == null) head = newNode;

        else {
            Node last = head;

            // else traverse the list to last node and insert it as next node
            while (true) {
                // make sure the inserted number is not in the list
                if (last.data == data) return false;
                else if (last.next != null) last = last.next;
                else break;
            }
            last.next = newNode;
        }
        return true;
    }

    @Override
    public boolean member(int data) {
        Node current = head;
        while (current != null) {
            if (current.data != data) current = current.next;
            else return true;
        }
        return false;
    }

    @Override
    public boolean delete(int data) {

        // had to keep track of previous node as well as current node
        Node previous = null, current = head;

        // traverse the list to find node with equivalent data
        while (current != null && current.data != data) {
            previous = current;
            current = current.next;
        }
        if (current == null) return false;

        else {
            // at this point current.data == data
            // and check if the head node actually has the data
            if (current == head) head = head.next;

                // else just connect previous to current.next
            else previous.next = current.next;
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node node = head;
        while (node != null) {
            sb.append(node.data).append(", ");
            node = node.next;
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }

    /**
     * Linked list Node
     * <p>
     * {@link Node#data} -> holds the data for the node
     * <p>
     * {@link Node#next} -> holds reference to next node
     */
    private static class Node {

        private final int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
