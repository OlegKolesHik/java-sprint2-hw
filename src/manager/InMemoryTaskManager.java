package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    public static Long taskIdNumber;
    public static Long subtaskIdNumber;
    public static Long epicIdNumber;
    public static HashMap<Long, Epic> epicT = new HashMap<>();
    public static HashMap<Long, Subtask> subtaskT = new HashMap<>();
    public static HashMap<Long, Task> taskT = new HashMap<>();
    protected TreeSet<Task> prioritizedTasks = new TreeSet<>(Comparator.comparing(Task::getStartTime));

    public static HistoryManager historyManager = Managers.getDefaultHistory();

        public InMemoryTaskManager(HistoryManager historyManager) {
            this.taskIdNumber = 0L;
            this.subtaskIdNumber = 0l;
            this.epicIdNumber = 0l;
        }


        private void updateEpicDurationAndStartTime(Long epicIdNumber){
            int durationEpic = 0;
            Epic epic = epicT.get(epicIdNumber);
            ArrayList<Long> subtaskId = epic.listIdSub();
            if(subtaskId.isEmpty()){
                epic.setDuration(0L);
                return;
            }
            LocalDateTime startEpic = LocalDateTime.MAX;
            LocalDateTime endEpic = LocalDateTime.MIN;
            long duration  = 0L;
            for (long id : subtaskId) {
                Subtask subtask = subtaskT.get(subtaskIdNumber);
                LocalDateTime startTime = subtask.getStartTime();
                LocalDateTime endTime = subtask.getEndTime();
                if (startTime.isBefore(startEpic)) {
                    startEpic = startTime;
                }
                if(endTime.isAfter(endEpic)) {
                    endEpic = endTime;

                }
                durationEpic += subtask.getDuration();
            }
            epic.setStartTime(startEpic);
            epic.setEndTime(endEpic);
            epic.setDuration(durationEpic);
        }

        private void validate(Task task) {
            LocalDateTime startTime = task.getStartTime();
            long duration = task.getDuration();
            LocalDateTime endTime = startTime.plusMinutes(duration);
            HashMap<Long, Task> allTasksMap = new HashMap<>();
            allTasksMap.putAll(taskT);
            allTasksMap.putAll(subtaskT);

            Integer counter = allTasksMap.values().stream()
                    .map(taskInner -> {
                        LocalDateTime thisStartTime = taskInner.getStartTime();
                        LocalDateTime thisEndTime = taskInner.getEndTime();
                        if (thisStartTime.isBefore(thisStartTime) && endTime.isBefore(thisStartTime))
                            return 1;
                        if (thisStartTime.isAfter(thisEndTime) && endTime.isAfter(thisEndTime))
                            return 1;
                        return 0;
                    }).reduce(Integer::sum)
                    .orElse(0);
            if (counter == allTasksMap.size())
                return;
            if (allTasksMap.isEmpty())
                return;
            if (counter != allTasksMap.size()) {
                throw new InvalidTimeException("Задачи пересекаются");
            }
        }

    public static HashMap<Long, Epic> getEpicT() {
        return epicT;
    }

    public static HashMap<Long, Subtask> getSubtaskT() {
        return subtaskT;
    }

    public static HashMap<Long, Task> getTaskT() {
        return taskT;
    }

    @Override
    public List<Task> getPrioritizedTasks(){
            return new ArrayList<Task>(prioritizedTasks);
    }

        public List<Object> gettingTask() {
            ArrayList<Object> ListIdTask = new ArrayList<>(); //Cписок всех задач
                ListIdTask.add(taskT);
                ListIdTask.add(subtaskT);
                ListIdTask.add(epicT);

            return ListIdTask;

        }
        public HashMap<Long, Subtask> gettingEpik (Long subtaskIdNumber) { //Получение списка всех подзадач определённого эпика
            Epic epic = epicT.get(gettingIdEpic(epicIdNumber));
            ArrayList<Long> ListId = epic.listIdSub();

            return subtaskT;
        }

        public Task getTask(Long taskIdNumber) {
            if(taskT.containsKey(taskIdNumber)) {
                historyManager.add(taskT.get(taskIdNumber));
                }
            return taskT.get(taskIdNumber);
        }

        public Subtask getSubtask(Long subtaskIdNumber) {
            if(subtaskT.containsKey(subtaskIdNumber)) {
            historyManager.add(subtaskT.get(subtaskIdNumber));
        }
            return subtaskT.get(subtaskIdNumber);
    }

        public Epic getEpic(Long epicIdNumber) {
            if(epicT.containsKey(epicIdNumber)) {
            historyManager.add(epicT.get(epicIdNumber));
        }
            return epicT.get(epicIdNumber);
    }
        @Override
        public List<Task> history() {
            return historyManager.getHistory();
    }
        //////////////////

        @Override
        public Map<Long, Task> creatureTask(Task task) { //Создание. Сам объект должен передаваться в качестве параметра.
            taskIdNumber++;
            task.setTaskIdNumber(taskIdNumber);
            taskT.put(taskIdNumber, task);
            return taskT;
        }

        @Override
        public Map<Long, Subtask> creatureSub(Subtask subtask) { //Создание. Сам объект должен передаваться в качестве параметра.
            if(subtask.getEpicIdNumber() != null) { // если эпик не пустой
               if(!epicT.containsKey(subtask.getEpicIdNumber())) { //и если ид эпика есть в epicT
                   taskIdNumber++; // увеличили значение id
                   subtask.setTaskIdNumber(taskIdNumber); // присвоили id это измененное значение
                   subtaskT.put(taskIdNumber, subtask); // добавили id и подзадачу в таблицу подзадач
               }
            }
        return subtaskT;
        }

        @Override
        public Map<Long, Epic> creatureEpic(Epic epic) { //Создание. Сам объект должен передаваться в качестве параметра.
            taskIdNumber++;
            epic.setTaskIdNumber(taskIdNumber);
            epicT.put(taskIdNumber, epic);
            return epicT;
    }

    @Override
       public Map<Long, Task> gettingIdTask(Long taskIdNumber) { //Получение по идентификатору
           taskT.get(taskIdNumber);
          return taskT;
    }

        @Override
        public Map<Long, Subtask> gettingIdSub(Long subtaskIdNumber) { //Получение по идентификатору
            subtaskT.get(subtaskIdNumber);
            return subtaskT;
        }

       @Override
       public Map<Long, Epic> gettingIdEpic(Long epicIdNumber) { //Получение по идентификатору
          epicT.get(epicIdNumber);
          return epicT;
    }

       @Override
       public void removeT(Long taskIdNumber) { //Удаление по идентификатору.
              this.taskT.remove(taskIdNumber);

    }
        @Override
        public void removeSub(Long subtaskIdNumber) { //Удаление по идентификатору.
            subtaskT.remove(subtaskIdNumber);
        }

        @Override
        public void removeEpic(Long epicIdNumber) { //Удаление по идентификатору.
            epicT.remove(epicIdNumber);
    }

        @Override
        public void clearTask() {  //Удаление всех задач
          taskT.clear();
    }

        @Override
        public void clearSub() {  //2.2 Удаление всех задач
            subtaskT.clear();
    }

        @Override
        public void clearEpic() {  //2.2  Удаление всех задач
            epicT.clear();
        }



}
