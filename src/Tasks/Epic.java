package Tasks;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private Long subtaskIdNumber;

    public Epic(String сaseName, String taskDescription, TaskStatus statusTask) { // свои параметры и параметры супер-класса
        super(сaseName, taskDescription, statusTask); // параметры супер-класса
        this.subtaskIdNumber = subtaskIdNumber;

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

    public void status() {
        int sNew = 0;
        int sDone = 0;
        int sInProgress = 0;

        ArrayList<String> st = new ArrayList<>();
        for (Integer k : subtaskT.keySet() ) {
            st.add(String.valueOf(subtaskT.get(k).getStatusTask()));
        }
         if (st.isEmpty()) {
              statusTask = TaskStatus.NEW;
            return;
         }
         if (statusTask.equals(TaskStatus.NEW)) {
            sNew = sNew + 1;
            return;
         }
         if (statusTask.equals(TaskStatus.DONE)) {
            sDone = sDone + 1;
             return;
         }
         if (statusTask.equals(TaskStatus.IN_PROGRESS)){
             sInProgress = sInProgress + 1;
             return;
         }
    }
}

