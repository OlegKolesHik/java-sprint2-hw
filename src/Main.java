import manager.*;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Main {
        public static void main(String[] args) {

                HistoryManager historyManager = Managers.getDefaultHistory();
                FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(historyManager);

                Task task = new Task("Задача", "Описание");
                Epic epic = new Epic("Эпик", "Описание эпика");
                Subtask subtask = new Subtask("Подзадача", "Описание подзадачи");

                fileBackedTasksManager.creatureTask(task);
                fileBackedTasksManager.creatureEpic(epic);
                fileBackedTasksManager.creatureSub(subtask);

                System.out.println(fileBackedTasksManager.getTask(task.getTaskIdNumber()));
                System.out.println(fileBackedTasksManager.getSubtask(subtask.getTaskIdNumber()));
                System.out.println(fileBackedTasksManager.getEpic(epic.getTaskIdNumber())+"\n");

                System.out.println("История просмотров: " + fileBackedTasksManager.gettingTask());

        }
}