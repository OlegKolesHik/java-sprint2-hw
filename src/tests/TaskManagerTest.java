package tests;

import manager.FileBackedTasksManager;
import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tasks.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static manager.InMemoryTaskManager.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TaskManagerTest<T extends TaskManager> { //дженерик для проверки каждой из реализаций TaskManager and FileBackedTaskManager

    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(historyManager);
    public T taskManager;
    public Task task;
    public Subtask subtask;
    public Epic epic;


    @BeforeEach
    public void taskManagerSetUp() {
        task = new Task(0L,"Task name", "Task description", TaskStatus.NEW,
                TaskType.TASK);
        epic = new Epic(0L,"Epic name", "Epic description", TaskStatus.NEW,
                TaskType.EPIC);
        subtask = new Subtask(0L,"Subtask name", "Subtask description", TaskStatus.NEW,
                TaskType.SUBTASK);

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
                TaskType.EPIC, task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        taskManager.creatureEpic(epic);
        TaskStatus status = taskManager.getEpic(epic.getTaskIdNumber()).getStatusTask();
        assertEquals(status, TaskStatus.NEW);

    }

    //b. Все подзадачи со статусом NEW.
    @Disabled
    @Test
    public void allSubtaskNew(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                TaskType.EPIC, task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        taskManager.creatureEpic(epic);
        epic.setDuration(15);
        Subtask subtask = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.NEW, TaskType.SUBTASK);
        subtask.setDuration(15);
        taskManager.creatureSub(subtask);
        TaskStatus status = taskManager.getEpic(epic.getTaskIdNumber()).getStatusTask();
        assertEquals(status, TaskStatus.NEW);

    }

    //c. Все подзадачи со статусом DONE.
    @Disabled
    @Test
    public void allSubtaskDone(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                TaskType.EPIC, task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        taskManager.creatureEpic(epic);
        Subtask subtask = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.DONE, TaskType.SUBTASK);
        subtask.setDuration(15);
        TaskStatus status1 = subtask.setStatusTask(TaskStatus.DONE);
        taskManager.creatureSub(subtask);
        TaskStatus status = taskManager.getEpic(0L).status();
        assertEquals(status, TaskStatus.DONE);
    }

    //d. Подзадачи со статусами NEW и DONE.
    @Disabled
    @Test
    public void allSubtaskDoneAndNew() {
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                TaskType.EPIC, task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        taskManager.creatureEpic(epic);
        Subtask subtask = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.NEW, TaskType.SUBTASK);
        subtask.setDuration(15);
        subtask.setStatusTask(TaskStatus.DONE);
        TaskStatus status1 = subtask.setStatusTask(TaskStatus.DONE);
        Subtask subtask2 = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.NEW, TaskType.SUBTASK);
        subtask.setDuration(15);
        subtask.setStatusTask(TaskStatus.NEW);
        taskManager.creatureSub(subtask);
        taskManager.creatureSub(subtask2);
        TaskStatus status = taskManager.getEpic(0L).status();
        assertEquals(status, TaskStatus.IN_PROGRESS);
    }

    //e. Подзадачи со статусом IN_PROGRESS
    @Disabled
    @Test
    public void allSubtaskInProgress() {
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic(0L,"Epic Tect", "Epic description", TaskStatus.NEW,
                TaskType.EPIC, task.getDuration(), LocalDateTime.of(2022, 8, 18, 18,
                30));
        epic.setDuration(15);
        taskManager.creatureEpic(epic);
        Subtask subtask = new Subtask(0L,"Subtask Tect", "Subtask description",
                TaskStatus.IN_PROGRESS, TaskType.SUBTASK);
        subtask.setDuration(15);
        taskManager.creatureSub(subtask);
        TaskStatus status = taskManager.getEpic(0L).status();
        assertEquals(status, TaskStatus.IN_PROGRESS);
    }

    @Test
    void clearTask() {
        inMemoryTaskManager.creatureTask(task);
        inMemoryTaskManager.creatureTask(task);
        assertEquals(2, taskT.size(), "Количество задач не совпадает");
        taskT.clear();
        assertEquals(0, taskT.size(), "Количество задач не совпадает");
    }

    @Test
    void clearSub() {
        inMemoryTaskManager.creatureSub(subtask);
        inMemoryTaskManager.creatureSub(subtask);
        assertEquals(2, subtaskT.size(), "Количество задач не совпадает");
        subtaskT.clear();
        assertEquals(0, subtaskT.size(), "Количество задач не совпадает");
    }

    @Test
    void clearEpic() {
        TaskManager taskManager = Managers.getDefault();
        taskManager.creatureEpic(epic);
        taskManager.creatureEpic(epic);
        assertEquals(2, epicT.size(), "Количество задач не совпадает");
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
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskT, "Ошибка получения задач");
        taskManager.creatureTask(task);
        assertEquals(1, taskT.size(), "Количество задач не совпадает");
        taskManager.creatureTask(task);
        assertEquals(2, taskT.size(), "Количество задач не совпадает");
    }

    @Test
    void creatureEpic() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(epicT, "Ошибка получения задач");
        taskManager.creatureEpic(epic);
        assertEquals(1, epicT.size(), "Количество задач не совпадает");
        taskManager.creatureEpic(epic);
        assertEquals(2, epicT.size(), "Количество задач не совпадает");
    }

    @Test
    void creatureSub() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(subtaskT, "Ошибка получения задач");
        taskManager.creatureSub(subtask);
        assertEquals(1, subtaskT.size(), "Количество задач не совпадает");
        taskManager.creatureSub(subtask);
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