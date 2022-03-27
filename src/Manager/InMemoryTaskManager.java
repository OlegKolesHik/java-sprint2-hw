package Manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private static HistoryManager historyManager = Managers.getDefaultHistory();

        public HashMap<Long, Epic> epicT;
        public HashMap<Long, Subtask> subtaskT;
        public HashMap<Long, Task> taskT;

        public InMemoryTaskManager() {
            epicT = new HashMap<Long, Epic>();
            subtaskT = new HashMap<Long, Subtask>();
            taskT = new HashMap<Long, Task>();

        }

        public ArrayList<Task> gettingTask() {
            ArrayList<Task> ListIdTask = new ArrayList<>(); //2.1 Cписок всех задач
            for (Long k : taskT.keySet()) {
                ListIdTask.add(taskT.get(k));
            }
            return ListIdTask;

        }
        public HashMap<Long, Subtask> gettingEpik (Long taskIdNumber) { //3.1 Получение списка всех подзадач определённого эпика
            Epic epic = epicT.get(taskIdNumber);
            ArrayList<Long> ListId = epic.listIdSub();

            return subtaskT;
        }

        public Task getTask(Long taskIdNumber) {
            if(taskT.containsKey(taskIdNumber)) {
                historyManager.add(taskT.get(taskIdNumber));
                }
            return taskT.get(taskIdNumber);
        }

        public Subtask getSubtask(Long SubtaskIdNumber) {
            if(taskT.containsKey(SubtaskIdNumber)) {
            historyManager.add(subtaskT.get(SubtaskIdNumber));
        }
            return subtaskT.get(SubtaskIdNumber);
    }

        public Epic getEpic(Long EpicIdNumber) {
            if(taskT.containsKey(EpicIdNumber)) {
            historyManager.add(epicT.get(EpicIdNumber));
        }
            return epicT.get(EpicIdNumber);
    }
        @Override
        public List<InMemoryHistoryManager.Node<Task>> history() {
            return historyManager.getHistory();
    }
        //////////////////

        @Override
        public void clear() {  //2.2  Удаление всех задач
            taskT.clear();
        }

        @Override
        public HashMap<Long, Task> gettingId(Long taskIdNumber) { //2.3 Получение по идентификатору
            taskT.get(taskIdNumber);
            return taskT;
        }

        @Override
        public HashMap<Long, Task> remove(Long taskIdNumber) { //2.6 Удаление по идентификатору.
            taskT.remove(taskIdNumber);
            return taskT;
        }

        @Override
        public HashMap<Long, Task> creature(Task task) { //2.4 Создание. Сам объект должен передаваться в качестве параметра.
            taskT.put(task.getTaskIdNumber(), task);
            return taskT;
        }
        ///////////////////////////

        @Override
        public void clearSub() {  //2.2 Удаление всех задач
            subtaskT.clear();
        }

        @Override
        public HashMap<Long, Subtask>gettingIdSub(Long subtaskIdNumber) { //2.3 Получение по идентификатору
            subtaskT.get(subtaskIdNumber);
            return subtaskT;
        }

        @Override
        public HashMap<Long, Subtask> removeSub(Long subtaskIdNumber) { //2.6 Удаление по идентификатору.
            subtaskT.remove(subtaskIdNumber);
            return subtaskT;
        }

        @Override
        public HashMap<Long, Subtask> creatureSub(Subtask subtask) { //2.4 Создание. Сам объект должен передаваться в качестве параметра.
            subtaskT.put(subtask.getTaskIdNumber(), subtask);
            return subtaskT;
        }
        ///////////////////////////

        @Override
        public void clearEpic() {  //2.2  Удаление всех задач
            epicT.clear();
        }

        @Override
        public HashMap<Long, Epic> gettingIdEpic(Long epicIdNumber) { //2.3 Получение по идентификатору
            epicT.get(epicIdNumber);
            return epicT;
        }

        @Override
        public HashMap<Long, Epic> removeEpic(Long epicIdNumber) { //2.6 Удаление по идентификатору.
            epicT.remove(epicIdNumber);
            return epicT;
        }

        @Override
        public HashMap<Long, Epic> creature(Epic epic) { //2.4 Создание. Сам объект должен передаваться в качестве параметра.
            epicT.put(epic.getTaskIdNumber(), epic);
            return epicT;
        }




}
