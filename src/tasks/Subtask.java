package tasks;

//в подзадаче хранить id эпика которому она принадлежит, а в эпике хранить список id подзадач.
public class Subtask extends Task {
    private Long epicIdNumber; //Параметр в рамках какого эпика она выполняется
    protected TaskStatus statusTask;

    public TaskStatus setStatusTask(TaskStatus statusTask) {
        this.statusTask = statusTask;
        return statusTask;
    }

    public Subtask(Long epicIdNumber, String сaseName, String taskDescription, TaskStatus NEW, TaskType SUBTASK) {
        super(epicIdNumber, сaseName, taskDescription, TaskStatus.NEW, TaskType.SUBTASK);
        this.epicIdNumber = epicIdNumber;
    }

    public Subtask(String s, String сaseName) {
        super(s, сaseName);
    }

    public Long getEpicIdNumber() {
        return epicIdNumber;
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "сaseName='" + сaseName + '\'' +
                ", SubtaskDescription='" + taskDescription + '\'' +
                ", IDSUBTASK=" + getTaskIdNumber() +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
}
