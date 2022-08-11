package tasks;

public class Task {

    protected String сaseName; // Название, кратко описывающее суть задачи (например, «Переезд»).
    protected String taskDescription; // Описание, в котором раскрываются детали.
    private Long taskIdNumber;
    protected TaskStatus statusTask; // Статус, отображающий её прогресс. Мы будем выделять следующие этапы жизни задачи:
    protected TaskType taskType;


    public Task(String сaseName, String taskDescription) {
        this.сaseName = сaseName;
        this.taskDescription = taskDescription;
        this.taskIdNumber = 0L;
        this.statusTask = TaskStatus.NEW;
        this.taskType = TaskType.TASK;
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
        return "Tasks.Task{" +
                "сaseName='" + сaseName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", IDTASK=" + getTaskIdNumber() +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }


}
