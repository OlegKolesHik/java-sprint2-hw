package Manager;

import Tasks.Task;
import java.util.*;


public class InMemoryHistoryManager implements HistoryManager {

    public static final int VIEWED_TASKS = 10;

//собирать все задачи из него(списка) в обычный ArrayList//
    public ArrayList<Task> allTasks;
    public Map<Long, Node> nodeMap = new HashMap<>();

    public InMemoryHistoryManager() {
    this.allTasks = new ArrayList<>();
}

// Элементы своего списка nodeMap в InMemoryHistoryManager
    private Node head;
    private Node tail;

// Добавляем последнюю ноду в конец списка nodeMap
    public void linkLast(Task task) {
        Node newTail = new Node(task, tail);
        if (tail != null) {
            tail.setNext(newTail);
        }
        if (head == null) {
            head = newTail;
        }
        tail = newTail;
        nodeMap.put(task.getTaskIdNumber(), newTail);
    }

    public void removeNode(Node node) {
        if (head == null) { // если лист пуст
            return; // то выходим из метода
        }
        if (head.getNext() == node) { //если головная нода равна нашему искомому значению
            head = head.getNext(); // то убираем(перешагиваем) наш элемент списка(идем дальше)
            return;
        }
        if (tail.getPrev() == node) {// если хвост равен нашему искомому значению
            tail = tail.getPrev();// то убираем (идем обратно)
            return;
        }
    }

//собирать все задачи из внутреннего списка в обычный ArrayList
    public ArrayList getTasks() {
        Node newTaskNode = head;
    while (newTaskNode != null) {
        allTasks.add(newTaskNode.getData());
       }
    return allTasks;
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
        if (nodeMap.containsKey(task.getTaskIdNumber())) {
            removeNode(nodeMap.remove(task.getTaskIdNumber()));
            linkLast(task);
        }
    }

//тз реализация метода getHistory должна перекладывать задачи из связного списка в ArrayList для формирования ответа.
    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(getTasks());
    }

    @Override
    public void remove(Task task) {
        if(nodeMap.isEmpty()) {
           if (nodeMap.containsKey(task.getTaskIdNumber())) {
            checkingReturnTasks();
        }
     }
   }
}






