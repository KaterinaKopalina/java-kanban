package service;

import model.Task;
import model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> nodesMap = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;

    private Node<Task> linkLast(Task task) {
        Node<Task> lastNode = tail;
        Node<Task> newNode = new Node(tail, task, null);
        if (lastNode == null) {
            head = newNode;
        } else {
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
        }
        tail = newNode;
        return newNode;
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node<Task> currentNode = head;
        while (currentNode != null) {
            tasks.add(currentNode.getItem());
            currentNode = currentNode.getNext();
        }
        return tasks;
    }

    @Override
    public void add(Task task) {
        if (task == null)
            return;
        if (nodesMap.containsKey(task.getId())) {
            remove(task.getId());
        }
        nodesMap.put(task.getId(), linkLast(task));
    }

    @Override
    public void remove(int id) {
        Node<Task> delNode = nodesMap.get(id);
        if (delNode != null) {
            removeNode(delNode);
            nodesMap.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public void removeNode(Node<Task> node) {
        Node<Task> prev = node.getPrev();
        Node<Task> next = node.getNext();

        if (prev == null) {
            head = next;
        } else {
            prev.setNext(next);
            node.setPrev(null);
        }

        if (next == null) {
            tail = prev;
        } else {
            next.setPrev(prev);
            node.setNext(null);
        }
    }
}