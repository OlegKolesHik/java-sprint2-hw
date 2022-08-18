package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static tasks.TaskStatus.NEW;

public class Epic extends Task {
    private Long subtaskIdNumber;
    private LocalDateTime endTime;
    protected TaskStatus statusTask;

    public Epic(Long subtaskIdNumber, String сaseName, String taskDescription, TaskStatus NEW, TaskType EPIC) { // свои параметры и параметры супер-класса
        super(subtaskIdNumber, сaseName, taskDescription, TaskStatus.NEW, TaskType.EPIC); // параметры супер-класса
        this.subtaskIdNumber = subtaskIdNumber;
    }

    public Epic(Long subtaskIdNumber, String сaseName, String taskDescription, TaskStatus NEW, TaskType EPIC,
                long duration, LocalDateTime startTime) {
        super(subtaskIdNumber, сaseName, taskDescription, TaskStatus.NEW, TaskType.EPIC, duration, startTime); // параметры супер-класса
        this.subtaskIdNumber = subtaskIdNumber;
    }

    public Epic(String s, String сaseName) {
        super(s, сaseName);
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatusTask(TaskStatus statusTask) {
        this.statusTask = statusTask;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Long getSubtaskIdNumber() {
        return subtaskIdNumber;
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "сaseName='" + сaseName + '\'' +
                ", EpicDescription='" + taskDescription + '\'' +
                ", IDEPIC=" + getTaskIdNumber() +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }

    public HashMap<Integer, Subtask> subtaskT;

    public ArrayList<Long> listIdSub() { //Список ID
        ArrayList<Long> ListId = new ArrayList<>();
        ListId.add(subtaskIdNumber);
        return ListId;
    }

    public TaskStatus status() {
        int sNew = 0;
        int sDone = 0;
        int sInProgress = 0;

        ArrayList<String> st = new ArrayList<>();
        for (Integer k : subtaskT.keySet() ) {
            st.add(String.valueOf(subtaskT.get(k).getStatusTask()));
        }
         if (st.isEmpty()) {
              statusTask = NEW;

         }
         if (statusTask.equals(NEW)) {
            sNew = sNew + 1;

         }
         if (statusTask.equals(TaskStatus.DONE)) {
            sDone = sDone + 1;

         }
         if (statusTask.equals(TaskStatus.IN_PROGRESS)){
             sInProgress = sInProgress + 1;

         }

        return null;
    }

}

