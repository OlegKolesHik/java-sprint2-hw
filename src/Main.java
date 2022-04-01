import Manager.*;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.TaskStatus;

public class Main {
    public static void main(String[] args) {

TaskManager taskManager = Managers.getDefault();
HistoryManager historyManager = Managers.getDefaultHistory();

//создайте две задачи, эпик с тремя подзадачами и эпик без подзадач;
Task task = new Task("Задача", "Описание", TaskStatus.NEW);
System.out.println("Создание задачи 1 " + taskManager.creatureTask(task));
System.out.println("Создание задачи 2 " + taskManager.creatureTask(task));
System.out.println("Создание задачи 3 " + taskManager.creatureTask(task));

Epic epic = new Epic("Эпик", "Описание эпика", TaskStatus.NEW);
System.out.println("Создание эпика 1 " + taskManager.creatureEpic(epic));

Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", TaskStatus.NEW);
System.out.println("Создание подзадачи 1 " + taskManager.creatureSub(subtask));
System.out.println("Создание подзадачи 2 " + taskManager.creatureSub(subtask));
System.out.println("Создание подзадачи 3 " + taskManager.creatureSub(subtask));

System.out.println("Создание эпика без подзадач " + taskManager.creatureEpic(epic));

//запросите созданные задачи несколько раз в разном порядке;
//после каждого запроса выведите историю и убедитесь, что в ней нет повторов;

System.out.println("ТАСК " + taskManager.getTask(task.getTaskIdNumber()));
System.out.println("ТАСК " + taskManager.getTask(task.getTaskIdNumber()));
System.out.println("ТАСК " + taskManager.getTask(task.getTaskIdNumber()));
System.out.println("История просмотров: " + taskManager.history());
System.out.println("САБТАСК " + taskManager.getSubtask(subtask.getTaskIdNumber()));
System.out.println("САБТАСК " + taskManager.getSubtask(subtask.getTaskIdNumber()));
System.out.println("САБТАСК " + taskManager.getSubtask(subtask.getTaskIdNumber()));
System.out.println("История просмотров: " + taskManager.history());
System.out.println("ЭПИК " + taskManager.getEpic(epic.getTaskIdNumber()));
System.out.println("ЭПИК " + taskManager.getEpic(epic.getTaskIdNumber()));
System.out.println("История просмотров: " + taskManager.history());

//удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться;
taskManager.removeT(task.getTaskIdNumber());
System.out.println("История просмотров:" + taskManager.history());

    }
}
