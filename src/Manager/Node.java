package Manager;

import Tasks.Task;
import java.util.Objects;

//Своя нода, своего LinkedList внутри InMemoryHistoryManager
public class Node {
    private Task data;
    private Node next;
    private Node prev;

    public Node(Task data, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    // Элементы своего списка nodeMap в InMemoryHistoryManager
    private Node head;
    private Node tail;

    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(data, node.data) && Objects.equals(next, node.next) && Objects.equals(prev, node.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, next, prev);
    }

    @Override
    public String toString() {
        return "Node{" +
                "taskIdNumber=" + data +
                ", next=" + next +
                ", prev=" + prev +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}
