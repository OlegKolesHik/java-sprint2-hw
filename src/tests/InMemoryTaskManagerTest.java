package tests;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;

import static manager.InMemoryTaskManager.historyManager;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    void setUp() {
        TaskManager taskManager = new InMemoryTaskManager(historyManager);
        taskManagerSetUp();
    }
}