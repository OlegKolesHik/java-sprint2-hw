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
System.out.println("Создание задачи 1 " + taskManager.creature(task));
System.out.println("Создание задачи 2 " + taskManager.creature(task));

Epic epic = new Epic("Эпик 1", "Описание 1 эпика", TaskStatus.NEW);
System.out.println("Создание эпика 1 " + taskManager.creature(epic));

Subtask subtask = new Subtask("Подзадача №1", "Описание 1 подзадачи", TaskStatus.NEW);
System.out.println("Создание подзадачи 1 " + taskManager.creatureSub(subtask));
System.out.println("Создание подзадачи 2 " + taskManager.creatureSub(subtask));
System.out.println("Создание подзадачи 3 " + taskManager.creatureSub(subtask));

System.out.println("Создание эпика без подзадач " + taskManager.creature(epic));

//запросите созданные задачи несколько раз в разном порядке;
//после каждого запроса выведите историю и убедитесь, что в ней нет повторов;
System.out.println(inMemoryTaskManager.getSubtask(1111111111L));
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());
System.out.println(inMemoryTaskManager.getTask(222222222222L));
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());
System.out.println(inMemoryTaskManager.getEpic(333333333333L));
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());

//удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться;
System.out.println("История просмотров: " + taskManager.removeEpic(epic.getTaskIdNumber()));
System.out.println("История просмотров: " + inMemoryHistoryManager.getHistory());

    }
}
