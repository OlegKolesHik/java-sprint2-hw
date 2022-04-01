package Manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    void clear();
    HashMap gettingId(Long taskIdNumber);
    void removeT(Long taskIdNumber);
    HashMap creatureTask(Task task);
    public Task getTask(Long taskIdNumber);
///////////////////////////

    void clearSub();
    HashMap gettingIdSub(Long subtaskIdNumber);
    HashMap removeSub(Long subtaskIdNumber);
    HashMap creatureSub(Subtask subtask);
    public Subtask getSubtask(Long SubtaskIdNumber);
///////////////////////////

    void clearEpic();
    HashMap gettingIdEpic(Long epicIdNumber);
    HashMap removeEpic(Long epicIdNumber);
    HashMap creatureEpic(Epic epic);
    public Epic getEpic(Long EpicIdNumber);

    public List<Task> history();
}

