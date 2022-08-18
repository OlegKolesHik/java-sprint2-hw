package tests;

import manager.FileBackedTasksManager;
import manager.TaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Task;

import java.io.File;
import java.util.List;

import static manager.InMemoryTaskManager.*;
import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {
FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(data);
    static String file = "data.csv";
    static File data;

    //Все общие методы написвны в TaskManagerTest

    @BeforeEach
    void setUp() {
        this.data = new File(file);
        data = new File("java-sprint2-hw/resources/data.csv");
        taskManagerSetUp();
    }

    @Disabled
    @Test
    void save(){}

    @Test
    void loadFromFile() {
        //сохранение в файал
        fileBackedTasksManager.getTask(task.getTaskIdNumber());
        fileBackedTasksManager.getSubtask(subtask.getTaskIdNumber());
        fileBackedTasksManager.getEpic(epic.getTaskIdNumber());
        //получили данные из файла
        FileBackedTasksManager fileBackedTasksManager1 = FileBackedTasksManager.loadFromFile(data);
        List<Object> tasks = fileBackedTasksManager1.gettingTask();
        assertNotNull(tasks, "");
        assertEquals(3, tasks.size(), "Количество задач отличается");
        assertEquals(taskT, tasks.get(0), "Задачи не совпадают");
        assertEquals(subtaskT, tasks.get(1), "Задачи не совпадают");
        assertEquals(epicT, tasks.get(2), "Задачи не совпадают");

    }
}