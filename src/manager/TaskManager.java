package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TaskManager {

    void clearTask();
    Map<Long, Task> gettingIdTask(Long taskIdNumber);
    void removeT(Long taskIdNumber);
    Map<Long, Task> creatureTask(Task task);
    public Task getTask(Long taskIdNumber);
///////////////////////////

    void clearSub();
    Map<Long, Subtask> gettingIdSub(Long subtaskIdNumber);
    void removeSub(Long subtaskIdNumber);
    Map<Long, Subtask> creatureSub(Subtask subtask);
    public Subtask getSubtask(Long SubtaskIdNumber);
///////////////////////////

    void clearEpic();
    Map<Long, Epic> gettingIdEpic(Long epicIdNumber);
    void removeEpic(Long epicIdNumber);
    Map<Long, Epic> creatureEpic(Epic epic);
    public Epic getEpic(Long EpicIdNumber);

    public List<Task> history();
    List<Task> getPrioritizedTasks();
}

