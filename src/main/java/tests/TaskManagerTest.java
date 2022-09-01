package tests;


import manager.FileBackedTasksManager;
import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tasks.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static manager.InMemoryTaskManager.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.FileBackedTasksManagerTest.data;

public abstract class TaskManagerTest<T extends TaskManager> { //дженерик для проверки каждой из реализаций TaskManager and FileBackedTaskManager

    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(historyManager);
    FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(data);

    public T taskManager;
    public Task task;
    public Subtask subtask;
    public Epic epic;


    public void taskManagerSetUp() {
       task = new Task(0L,"Task name", "Task description", TaskStatus.NEW, 10l,
                LocalDateTime.now());
        fileBackedTasksManager.creatureTask(task);
        epic = new Epic(0L,"Epic name", "Epic description", TaskStatus.NEW,0l,
                task.getEndTime());
        fileBackedTasksManager.creatureEpic(epic);
        subtask = new Subtask(0L,"Subtask name", "Subtask description", TaskStatus.NEW,
                epic.getTaskIdNumber(), 10l, task.getEndTime());
        fileBackedTasksManager.creatureSub(subtask);

    }

    @Test
    void gettingTask() {
        ArrayList<Object> ListIdTask = new ArrayList<>();
        assertTrue(ListIdTask.isEmpty(),"Список задач не пуст");
        ListIdTask.add(taskT);
        assertEquals(1, ListIdTask.size(), "Task не добавлен. Количество задач не совпало");
        ListIdTask.add(subtaskT);
        assertEquals(2, ListIdTask.size(), "Subtask не добавлен. Количество задач не совпало");
        ListIdTask.add(epicT);
        assertEquals(3, ListIdTask.size(), "Epic не добавлен. Количество задач не совпало");
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertEquals(3, ListIdTask.size(), "Количество задач не совпало");
        assertEquals(taskT, ListIdTask.get(0), "Не получили Task");
        assertEquals(subtaskT, ListIdTask.get(1), "Не получили Subtask");
        assertEquals(epicT, ListIdTask.get(2), "Не получили Epic");
    }


    @Test
    void allTasksNew(){
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Тест", "Epic description", TaskStatus.NEW,
                 task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        fileBackedTasksManager.creatureEpic(epic);
        TaskStatus status = taskManager.getEpic(epic.getTaskIdNumber()).getStatusTask();
        assertEquals(status, TaskStatus.NEW);

    }

    //b. Все подзадачи со статусом NEW.

    @Test
    public void allSubtaskNew(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                 task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        fileBackedTasksManager.creatureEpic(epic);
        epic.setDuration(15);
        Subtask subtask = new Subtask(0L,"Subtask name", "Subtask description", TaskStatus.NEW,
                epic.getTaskIdNumber(), 10l, task.getEndTime());
        subtask.setDuration(15);
        fileBackedTasksManager.creatureSub(subtask);
        TaskStatus status = taskManager.getEpic(epic.getTaskIdNumber()).getStatusTask();
        assertEquals(status, TaskStatus.NEW);

    }

    //c. Все подзадачи со статусом DONE.

    @Test
    public void allSubtaskDone(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                 task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        fileBackedTasksManager.creatureEpic(epic);
        Subtask subtask = new Subtask(0L,"Subtask name", "Subtask description", TaskStatus.NEW,
                epic.getTaskIdNumber(), 10l, task.getEndTime());
        subtask.setDuration(15);
        subtask.setStatusTask(TaskStatus.DONE);
        fileBackedTasksManager.creatureSub(subtask);
        TaskStatus status = subtask.getStatusTask();
        assertEquals(status, TaskStatus.DONE);
    }

    //d. Подзадачи со статусами NEW и DONE.

    @Test
    public void allSubtaskDoneAndNew() {
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                 task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        fileBackedTasksManager.creatureEpic(epic);
        Subtask subtask = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.NEW, TaskType.SUBTASK);
        subtask.setDuration(15);
        subtask.setStatusTask(TaskStatus.DONE);
        TaskStatus status1 = subtask.setStatusTask(TaskStatus.DONE);
        Subtask subtask2 = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.NEW, TaskType.SUBTASK);
        subtask.setDuration(15);
        subtask.setStatusTask(TaskStatus.NEW);
        fileBackedTasksManager.creatureSub(subtask);
        fileBackedTasksManager.creatureSub(subtask2);
        TaskStatus status = subtask.getStatusTask();
        assertEquals(status, TaskStatus.NEW);
    }

    //e. Подзадачи со статусом IN_PROGRESS

    @Test
    public void allSubtaskInProgress() {
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        fileBackedTasksManager.creatureEpic(epic);
        Subtask subtask = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.NEW, TaskType.SUBTASK);
        subtask.setDuration(15);
        subtask.setStatusTask(TaskStatus.IN_PROGRESS);
        fileBackedTasksManager.creatureSub(subtask);
        TaskStatus status = subtask.getStatusTask();
        assertEquals(status, TaskStatus.IN_PROGRESS);
    }

    @Test
    void clearTask() {
        Task task = new Task("Тест 1", "Описание");
        Task task1 = new Task("Тест 2", "Описание");
        fileBackedTasksManager.creatureTask(task);
        fileBackedTasksManager.creatureTask(task1);
        assertEquals(2, taskT.size(), "Количество задач не совпадает");
        taskT.clear();
        assertEquals(0, taskT.size(), "Количество задач не совпадает");
    }

    @Test
    void clearSub() {
        Epic epic = new Epic("Тест 1", "Описание");
        Subtask subtask = new Subtask("Тест 1", "Описание");
        Subtask subtask1 = new Subtask("Тест 2", "Описание");
        fileBackedTasksManager.creatureEpic(epic);
        fileBackedTasksManager.creatureSub(subtask);
        fileBackedTasksManager.creatureSub(subtask1);
        assertEquals(2, subtaskT.size(), "Количество задач не совпадает");
        subtaskT.clear();
        assertEquals(0, subtaskT.size(), "Количество задач не совпадает");
    }

    @Test
    void clearEpic() {
        Epic epic = new Epic("Тест 1", "Описание");
        Epic epic1 = new Epic("Тест 2", "Описание");
        fileBackedTasksManager.creatureEpic(epic);
        fileBackedTasksManager.creatureEpic(epic1);
        assertEquals(3, epicT.size(), "Количество задач не совпадает");
        epicT.clear();
        assertEquals(0, epicT.size(), "Количество задач не совпадает");
    }

    @Test
    void removeT() {
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertNotNull(tasks, "Ошибка получения задач");
        assertEquals(3, tasks.size(), "Количество задач не совпадает");
        tasks.remove(0);
        assertEquals(2, tasks.size(), "Количество задач не совпадает");
    }

    @Test
    void removeEpic() {
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertNotNull(tasks, "Ошибка получения задач");
        assertEquals(3, tasks.size(), "Количество задач не совпадает");
        tasks.remove(1);
        assertEquals(2, tasks.size(), "Количество задач не совпадает");
    }

    @Test
    void removeSub() {
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertNotNull(tasks, "Ошибка получения задач");
        assertEquals(3, tasks.size(), "Количество задач не совпадает");
        tasks.remove(2);
        assertEquals(2, tasks.size(), "Количество задач не совпадает");
    }

    @Test
    void creatureTask() {
        assertNotNull(taskT, "Ошибка получения задач");
        Task task = new Task("Тест 1", "Описание");
        fileBackedTasksManager.creatureTask(task);
        Task task1 = new Task("Тест 2", "Описание");
        fileBackedTasksManager.creatureTask(task1);
        assertEquals(2, taskT.size(), "Количество задач не совпадает");
    }

    @Test
    void creatureEpic() {
        assertNotNull(epicT, "Ошибка получения задач");
        Epic epic = new Epic("Тест 1", "Описание");
        fileBackedTasksManager.creatureEpic(epic);
        Epic epic1 = new Epic("Тест 2", "Описание");
        fileBackedTasksManager.creatureEpic(epic1);
        assertEquals(3, epicT.size(), "Количество задач не совпадает");
    }

    @Test
    void creatureSub() {
        assertNotNull(subtaskT, "Ошибка получения задач");
        Subtask subtask = new Subtask("Тест 1", "Описание");
        Subtask subtask1 = new Subtask("Тест 2", "Описание");
        fileBackedTasksManager.creatureSub(subtask);
        fileBackedTasksManager.creatureSub(subtask1);
        assertEquals(2, subtaskT.size(), "Количество задач не совпадает");

    }

    @Test
    void gettingIdTask() {
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertNotNull(tasks, "Ошибка получения задач");
        assertEquals(3, tasks.size(), "Количество задач не совпадает");
        assertEquals(taskT, tasks.get(0), "Задача(Task) не совпадает");
    }

    @Test
    void gettingIdEpic() {
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertNotNull(tasks, "Ошибка получения задач");
        assertEquals(3, tasks.size(), "Количество задач не совпадает");
        assertEquals(epicT, tasks.get(2), "Задача(Task) не совпадает");
    }

    @Test
    void gettingIdSub() {
        List<Object> tasks = inMemoryTaskManager.gettingTask();
        assertNotNull(tasks, "Ошибка получения задач");
        assertEquals(3, tasks.size(), "Количество задач не совпадает");
        assertEquals(subtaskT, tasks.get(1), "Задача(Task) не совпадает");
    }

    @Test
    void history() {
        assertNotNull(historyManager.getHistory(), "История пустая");
    }
}