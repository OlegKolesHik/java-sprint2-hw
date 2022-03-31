package Manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {


    public Long taskIdNumber;
    public HashMap<Long, Epic> epicT;
    public HashMap<Long, Subtask> subtaskT;
    public HashMap<Long, Task> taskT;

    public HistoryManager historyManager = Managers.getDefaultHistory();

        public InMemoryTaskManager() {
            this.taskIdNumber = 0L;
            this.epicT = new HashMap<Long, Epic>();
            this.subtaskT = new HashMap<Long, Subtask>();
            this.taskT = new HashMap<Long, Task>();

        }
Subtask subtask;
        public ArrayList<Task> gettingTask() {
            ArrayList<Task> ListIdTask = new ArrayList<>(); //2.1 Cписок всех задач
            for (Long k : taskT.keySet()) {
                ListIdTask.add(taskT.get(k));
            }
            return ListIdTask;

        }
        public HashMap<Long, Subtask> gettingEpik (Long taskIdNumber) { //3.1 Получение списка всех подзадач определённого эпика
            Epic epic = epicT.get(subtask.getEpicIdNumber());
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
            if(subtaskT.containsKey(SubtaskIdNumber)) {
            historyManager.add(subtaskT.get(SubtaskIdNumber));
        }
            return subtaskT.get(SubtaskIdNumber);
    }

        public Epic getEpic(Long EpicIdNumber) {
            if(epicT.containsKey(EpicIdNumber)) {
            historyManager.add(epicT.get(EpicIdNumber));
        }
            return epicT.get(EpicIdNumber);
    }
        @Override
        public List<Task> history() {
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
        public HashMap creatureTask(Task task) { //2.4 Создание. Сам объект должен передаваться в качестве параметра.
            taskIdNumber++;
            task.setTaskIdNumber(taskIdNumber);
            taskT.put(taskIdNumber, task);
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
        public HashMap creatureSub(Subtask subtask) { //2.4 Создание. Сам объект должен передаваться в качестве параметра.
            if(subtask.getEpicIdNumber() != null) { // если эпик не пустой
                if(epicT.containsKey(subtask.getEpicIdNumber())) { //и если ид эпика есть в epicT
                    taskIdNumber++; // увеличили значение id
                    subtask.setTaskIdNumber(taskIdNumber); // присвоили id это измененное значение
                    subtaskT.put(taskIdNumber, subtask); // добавили id и подзадачу в таблицу подзадач
                }
            }
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
        public HashMap creatureEpic(Epic epic) { //2.4 Создание. Сам объект должен передаваться в качестве параметра.
            taskIdNumber++;
            epic.setTaskIdNumber(taskIdNumber);
            epicT.put(taskIdNumber, epic);
            return epicT;
        }




}
