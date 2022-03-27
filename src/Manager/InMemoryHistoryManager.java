package Manager;

import Tasks.Task;
import java.util.*;


public class InMemoryHistoryManager implements HistoryManager {

    public static final int VIEWED_TASKS = 10;

//собирать все задачи из него(списка) в обычный ArrayList//
    public List<Node<Task>> allTasks;
    public Map<Long, Node<Task>> historyList = new HashMap<>();

    public InMemoryHistoryManager() {
    this.allTasks = new ArrayList<Node<Task>>();
}

// Элементы своего списка historyList в InMemoryHistoryManager
    public Node<Task> head;
    public Node<Task> tail;
    public Node<Task> newTaskNode; // новая нода
    public Node<Task> pastTaskNode; //Прошлая нод (до нашей newTaskNode)

//Своя нода, своего LinkedList внутри InMemoryHistoryManager
    public class Node<Task> {
        private Task data;
        private Node<Task> next;
        private Node<Task> prev;

        public Node(Task task) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

// Добавляем последнюю ноду в конец списка historyList
    public void linkLast(Task task) {
        newTaskNode = tail; //поместили ноду в хвост
        newTaskNode.next = null; //ссылка на null
        newTaskNode.prev = pastTaskNode; //ссылка новой на прошлую ноду
        pastTaskNode.next = newTaskNode; //ссылка прошлой на новую ноду
        historyList.put(task.getTaskIdNumber(), newTaskNode); //добавили все в список historyList

    }

    public void removeNode(Node node) {
        if (head == null) { // если лист пуст
            return; // то выходим из метода
        }
        if (head.next == node.data) { //если головная нода равна нашему искомому значению
            head = head.next; // то убираем(перешагиваем) наш элемент списка(идем дальше)
            return;
        }
        if (tail.prev == node.data) {// если хвост равен нашему искомому значению
            tail = tail.prev;// то убираем (идем обратно)
            return;
        }
    }

//собирать все задачи из внутреннего списка в обычный ArrayList
    public void getTasks() {
        allTasks.add(head);
        allTasks.add(tail);
        allTasks.add(newTaskNode);
        allTasks.add(pastTaskNode);
    }

    private void checkingReturnTasks() {
        if (allTasks.size() > VIEWED_TASKS) {
            allTasks.remove(0);
        }
    }

//тз метод add(Task task) будет быстро удалять задачу из списка, если она там есть, а затем вставлять
//её в конец двусвязного списка.
    @Override
    public void add(Task task) {
        if (historyList.containsKey(task.getTaskIdNumber())) {
            removeNode(historyList.remove(task.getTaskIdNumber()));
            linkLast(task);
        }
    }

//тз реализация метода getHistory должна перекладывать задачи из связного списка в ArrayList для формирования ответа.
    @Override
    public List<Node<Task>> getHistory() {
        for(Node value : historyList.values()) {
            allTasks.add(value);
        }
   return allTasks;
    }

    @Override
    public void remove(Task task) {
        if (historyList.containsKey(task.getTaskIdNumber())) {
            checkingReturnTasks();
        }

    }
}






