import Manager.*;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

public class Main {
    public static void main(String[] args) {

TaskManager taskManager = new Managers().getDefault();
InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

Task task = new Task("Задача", "Описание", "");
taskManager.creature(task);
System.out.println("Создание задачи" + taskManager.creature(task));

Subtask subtask = new Subtask("Подзадача №1", "Описание 1 подзадачи", "");
taskManager.creatureSub(subtask);
System.out.println("Создание подзадачи" + taskManager.creatureSub(subtask));

Epic epic = new Epic("Эпик 1", "Описание 1 эпика", "");
taskManager.creature(epic);
System.out.println("Создание эпика" + taskManager.creature(epic));
////////////////////////

inMemoryTaskManager.getTask(44444l);
taskManager.gettingId(333333l);
System.out.println(taskManager.history());

taskManager.creatureSub(subtask);
inMemoryTaskManager.getEpic(44444444l);
System.out.println(taskManager.history());

taskManager.clear();
System.out.println(taskManager.history());

    }
}
