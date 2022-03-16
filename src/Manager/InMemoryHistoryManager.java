package Manager;

import Tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    ///Подумайте, каким образом и какие данные вы запишете в поля менеджера для возможности извлекать из них
    // историю посещений

    private List<Task> listTask;

    public InMemoryHistoryManager() {
        this.listTask = new ArrayList<>();
    }

    /*Если размер списка исчерпан,
    из него нужно удалить самый старый элемент — тот который находится в начале списка. */
    private void checkingReturnTasks () {
        if (this.listTask.size() > 10) {
            this.listTask.remove(0);
        }
    }

    @Override
    public void add(Task task) {  //просмотренные задачи
        checkingReturnTasks();
          this.listTask.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return listTask;
    }



}
