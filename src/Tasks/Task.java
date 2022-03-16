package Tasks;

public class Task {

    protected String сaseName; // Название, кратко описывающее суть задачи (например, «Переезд»).
    protected String taskDescription; // Описание, в котором раскрываются детали.
    private Long taskIdNumber;
    protected String statusTask; // Статус, отображающий её прогресс. Мы будем выделять следующие этапы жизни задачи:

    public Task(String сaseName, String taskDescription, String statusTask) {
        this.сaseName = сaseName;
        this.taskDescription = taskDescription;
        taskIdNumber = 0L;
        this.statusTask = statusTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public Long getTaskIdNumber() {
        return taskIdNumber;
    }

    @Override
    public String toString() {
        return "Tasks.Task{" +
                "сaseName='" + сaseName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskIdNumber=" + taskIdNumber +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
}
