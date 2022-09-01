package tasks;

import java.time.LocalDateTime;

public class Task {

    protected String сaseName; // Название, кратко описывающее суть задачи (например, «Переезд»).
    protected String taskDescription; // Описание, в котором раскрываются детали.
    private Long taskIdNumber;
    protected TaskStatus statusTask; // Статус, отображающий её прогресс. Мы будем выделять следующие этапы жизни задачи:
    protected TaskType taskType;
    protected long duration;
    protected LocalDateTime startTime;


    public Task(Long taskIdNumber, String сaseName, String taskDescription, TaskStatus NEW, TaskType TASK) {
        this.сaseName = сaseName;
        this.taskDescription = taskDescription;
        this.taskIdNumber = 0L;
        this.statusTask = TaskStatus.NEW;
        this.taskType = TaskType.TASK;
    }

    public Task(Long taskIdNumber, String сaseName, String taskDescription, TaskStatus NEW, long duration,
                LocalDateTime startTime) {
        this.сaseName = сaseName;
        this.taskDescription = taskDescription;
        this.taskIdNumber = 0L;
        this.statusTask = TaskStatus.NEW;
        this.taskType = TaskType.TASK;
        this.duration = duration;
        this.startTime = startTime;
    }
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return startTime.plusMinutes(duration);
    }

    public Task(String s, String сaseName) {
    }

    public TaskStatus getStatusTask() {
        return TaskStatus.NEW;
    }

    public Long getTaskIdNumber() {
        return taskIdNumber;
    }

    public String getСaseName() {
        return сaseName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskIdNumber(Long taskIdNumber) {
        this.taskIdNumber = taskIdNumber;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "{Tasks.Task{" +
                "сaseName='" + сaseName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", IDTASK=" + getTaskIdNumber() +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }


}
