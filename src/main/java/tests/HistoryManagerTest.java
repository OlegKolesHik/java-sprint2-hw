package tests;

import manager.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tests.FileBackedTasksManagerTest.data;

class HistoryManagerTest {

    HistoryManager historyManager;
    private tasks.Task task;
    private tasks.Epic epic;
    private tasks.Subtask subtask;
    FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(data);
    TaskManager taskManager = Managers.getDefault();

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task = new Task(0L,"Task name", "Task description", TaskStatus.NEW, 10l,
                LocalDateTime.now());
        epic = new Epic(0L,"Epic name", "Epic description", TaskStatus.NEW,0l,
                task.getEndTime());
        subtask = new Subtask(0L,"Subtask name", "Subtask description", TaskStatus.NEW,
                epic.getTaskIdNumber(), 10l, task.getEndTime());
    }


    //a. Пустая история задач.
    @Test
    void getHistory() {
        List<tasks.Task> history = historyManager.getHistory();
        assertNotNull(history, "Пустая история задач."); //Проверяет, что аргумент не равен null, если равен то сообщение (факт, ожидание, сообщение при  неудаче теста)
        assertTrue(history.isEmpty(),"Не пустая история задач.");
    }

    //b. Дублирование.
    @Test
    void add() {
        historyManager.add(task);
        final List<tasks.Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(1, history.size(), "Размер истории отличается.");
    }

    //с. Удаление из истории: начало, середина, конец.
    @Test
    void removeFirst() {
        historyManager.add(task); // добавление истории
        historyManager.add(epic);
        historyManager.add(subtask);
        List<tasks.Task> history = historyManager.getHistory(); //получение это истории
        assertNotNull(history, "История пустая."); // проверяем, что история не null и экземпляр списка
        // создан, если пустая то сообщение
        assertEquals(3, history.size(), "Размер истории отличается."); //размер истории - 3
        historyManager.remove(task.getTaskIdNumber());
        history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(2, history.size(), "Ошибка удаления истории"); //проверяем, что осталось только
        // два значения - эпика и сабтаска
        assertEquals(epic, history.get(0), "Остался Epic");
        assertEquals(subtask, history.get(1), "Остался Subtask");
    }

    @Test
    void removeMiddle() {
        historyManager.add(task); // добавление истории
        historyManager.add(epic);
        historyManager.add(subtask);
        TaskManager taskManager = Managers.getDefault();
        List<Task> history = taskManager.history(); //получение это истории
        assertNotNull(history, "История пустая."); // проверяем, что история не null и экземпляр списка
        // создан, если пустая то сообщение
        assertEquals(3, history.size(), "Размер истории отличается."); //размер истории - 3
        historyManager.remove(epic.getTaskIdNumber());
        history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(2, history.size(), "Ошибка удаления истории"); //проверяем, что осталось только
        // два значения - эпика и сабтаска
        assertEquals(task, history.get(0), "Остался Task");
        assertEquals(subtask, history.get(1), "Остался Subtask");
    }

    @Test
    void removeLast() {
        historyManager.add(task); // добавление истории
        historyManager.add(epic);
        historyManager.add(subtask);
        List<Task> history = historyManager.getHistory(); //получение это истории
        assertNotNull(history, "История пустая."); // проверяем, что история не null и экземпляр списка
        // создан, если пустая то сообщение
        assertEquals(3, history.size(), "Размер истории отличается."); //размер истории - 3
        historyManager.remove(subtask.getTaskIdNumber());
        history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(2, history.size(), "Ошибка удаления истории"); //проверяем, что осталось только
        // два значения - эпика и сабтаска
        assertEquals(task, history.get(0), "Остался Task");
        assertEquals(epic, history.get(1), "Остался Epic");
    }


    @Test
    void addTwice() {
        historyManager.add(task);
        historyManager.add(task);
        final List<tasks.Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(1, history.size(), "Есть дубли");
    }

}