package Manager;

import Tasks.Task;
import java.util.*;



public class InMemoryHistoryManager implements HistoryManager {

    public static final int VIEWED_TASKS = 10;

//собирать все задачи из него(списка) в обычный ArrayList//
    public ArrayList<Task> allTasks = new ArrayList<>();
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
        Long idRemove = node.getData().getTaskIdNumber(); // // достали id задачи, поместили в idRemove
        if (nodeMap.containsKey(idRemove)) { //если id есть, далее работаем с ним, id имеет(prev/id/next)
            Node prevNode = node.getPrev();  //prevNode это прошлая нода (наш id задачи с двумя ссылками prev и next)
            Node nextNode = node.getNext();//nextNode это следующая нода (наш id задачи с двумя ссылками prev и next)
            if (prevNode != null) {  //если prevNode не пустая, т.е. содержит id
                prevNode.setNext(nextNode);// то у нашей prevNode вызываем переход next к следующему id (nextNode)
            } else { //иначе,если пустая
                nextNode.setPrev(null);  //то значит наш nextNode со ссылкой prev будет ссылаться на null
                head = nextNode; //head присвоили nextNode
            }
            if (nextNode != null) {// если id в nextNode есть(существует)
                nextNode.setPrev(prevNode);//то у ноды nextNode вызываем переход от prev к нашему id(связываем) prevNode
            } else {
                prevNode.setNext(null);  // иначе prevNode своим next будет ссылаться на null
                tail = prevNode; // tail присвоили prevNode
            }
            nodeMap.remove(idRemove);
        }
    }

//собирать все задачи из внутреннего списка в обычный ArrayList
    public ArrayList<Task> getTasks() {
        Node newTaskNode = head; //newTaskNode является головой списка
    while (newTaskNode != null) {
        allTasks.add(newTaskNode.getData()); // добавлять до тех пор, пока newTaskNode не будет ссылаться next на null
        newTaskNode = newTaskNode.getPrev(); // одновременно делаем возврат от prev нашей newTaskNode к нашей data
       }
        Collections.reverse(allTasks);
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
        return getTasks();
    }

    @Override
    public void remove(Task task) {
        if(!nodeMap.isEmpty()) {
           if(nodeMap.containsKey(task.getTaskIdNumber())) {
            checkingReturnTasks();
        }
     }
   }
}






