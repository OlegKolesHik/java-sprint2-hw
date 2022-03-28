package Manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    void clear();
    HashMap gettingId(Long taskIdNumber);
    HashMap remove(Long taskIdNumber);
    HashMap creature(Task task);
///////////////////////////

    void clearSub();
    HashMap gettingIdSub(Long subtaskIdNumber);
    HashMap removeSub(Long subtaskIdNumber);
    HashMap creatureSub(Subtask subtask);
///////////////////////////

    void clearEpic();
    HashMap gettingIdEpic(Long epicIdNumber);
    HashMap removeEpic(Long epicIdNumber);
    HashMap creature(Epic epic);

    public List<Task> history();
}

