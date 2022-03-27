package Tasks;

public class Task {

    protected String сaseName; // Название, кратко описывающее суть задачи (например, «Переезд»).
    protected String taskDescription; // Описание, в котором раскрываются детали.
    private Long taskIdNumber;
    protected TaskStatus statusTask; // Статус, отображающий её прогресс. Мы будем выделять следующие этапы жизни задачи:

    public Task(String сaseName, String taskDescription, TaskStatus statusTask) {
        this.сaseName = сaseName;
        this.taskDescription = taskDescription;
        taskIdNumber = 0L;
        this.statusTask = TaskStatus.NEW;
    }

    public TaskStatus getStatusTask() {
        return TaskStatus.NEW;
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
