import Manager.*;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.TaskStatus;

public class Main {
    public static void main(String[] args) {

TaskManager taskManager = new Managers().getDefault();
InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

//создайте две задачи, эпик с тремя подзадачами и эпик без подзадач;
Task task = new Task("Задача", "Описание", TaskStatus.NEW);
System.out.println("Создание задачи 1 " + taskManager.creatureTask(task));
System.out.println("Создание задачи 2 " + taskManager.creatureTask(task));

Epic epic = new Epic("Эпик 1", "Описание 1 эпика", TaskStatus.NEW);
System.out.println("Создание эпика 1 " + taskManager.creatureTask(epic));

Subtask subtask = new Subtask("Подзадача №1", "Описание 1 подзадачи", TaskStatus.NEW);
System.out.println("Создание подзадачи 1 " + taskManager.creatureSub(subtask));
System.out.println("Создание подзадачи 2 " + taskManager.creatureSub(subtask));
System.out.println("Создание подзадачи 3 " + taskManager.creatureSub(subtask));

System.out.println("Создание эпика без подзадач " + taskManager.creatureEpic(epic));

//запросите созданные задачи несколько раз в разном порядке;
//после каждого запроса выведите историю и убедитесь, что в ней нет повторов;
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());
inMemoryTaskManager.getSubtask(subtask.getTaskIdNumber());
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());
inMemoryTaskManager.getTask(task.getTaskIdNumber());
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());

//удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться;
taskManager.removeEpic(epic.getTaskIdNumber());
System.out.println("История просмотров:" + inMemoryHistoryManager.getHistory());
    }
}
