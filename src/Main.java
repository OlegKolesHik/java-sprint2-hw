import manager.*;

import tasks.*;

import java.io.File;

public class Main {
        public static void main(String[] args) {

                HistoryManager historyManager = Managers.getDefaultHistory();
                FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(
                        new File("java-sprint2-hw/resources/data.csv"));
                InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(historyManager);
                InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

                Task task = new Task(0L,"Task name", "Task description",
                        TaskStatus.NEW, TaskType.TASK);
                Epic epic = new Epic(0L,"Epic name", "Epic description",
                        TaskStatus.NEW, TaskType.EPIC);
                Subtask subtask = new Subtask(0L,"Subtask name", "Subtask description",
                        TaskStatus.NEW, TaskType.SUBTASK);

                fileBackedTasksManager.creatureTask(task);
                fileBackedTasksManager.creatureEpic(epic);
                fileBackedTasksManager.creatureSub(subtask);
                System.out.println(fileBackedTasksManager.getTask(task.getTaskIdNumber()));
                System.out.println(fileBackedTasksManager.getSubtask(subtask.getTaskIdNumber()));
                System.out.println(fileBackedTasksManager.getEpic(epic.getTaskIdNumber()));
                System.out.println("История просмотров: " + fileBackedTasksManager.gettingTask()+"\n");

        }
}