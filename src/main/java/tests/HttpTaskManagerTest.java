package tests;

import client.KVTaskClient;
import http.HttpTaskManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import server.KVServer;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class HttpTaskManagerTest extends TaskManagerTest<HttpTaskManager> {

    @AfterEach
    void tearDown() {
        taskManager.getKvSever().stop();
    }

    @BeforeEach
    void setUp() throws IOException {
        taskManager = new HttpTaskManager(KVServer.PORT);
    }

    @Disabled
    @Test
    void save() {
    }

    @Test
    void load() {
        taskManager.getTask(task.getTaskIdNumber());

        HttpTaskManager newTaskManager = new HttpTaskManager(8078);
        newTaskManager.load(new KVTaskClient(KVServer.PORT));

        List<Object> taskS = newTaskManager.gettingTask();
        assertNotNull(taskS, "Получили пустой список");
        assertEquals(1, taskS.size(), "Разное кол-во задач");
        assertEquals(task, taskS.get(0), "Несовпадение задач");

    }
}