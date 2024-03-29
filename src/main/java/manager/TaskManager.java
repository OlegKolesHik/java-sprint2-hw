package manager;

import server.KVServer;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TaskManager {

    void clearTask();
    Map<Long, Task> gettingIdTask(Long taskIdNumber);
    void removeT(Long taskIdNumber);
    void creatureTask(Task task);
    public Task getTask(Long taskIdNumber);
///////////////////////////

    void clearSub();
    Map<Long, Subtask> gettingIdSub(Long subtaskIdNumber);
    void removeSub(Long subtaskIdNumber);
    void creatureSub(Subtask subtask);
    public Subtask getSubtask(Long SubtaskIdNumber);
///////////////////////////

    void clearEpic();
    Map<Long, Epic> gettingIdEpic(Long epicIdNumber);
    void removeEpic(Long epicIdNumber);
    void creatureEpic(Epic epic);
    public Epic getEpic(Long EpicIdNumber);

    public List<Task> history();
    ArrayList<Task> getPrioritizedTasks();


}

