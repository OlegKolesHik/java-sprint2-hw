package Tasks;

//в подзадаче хранить id эпика которому она принадлежит, а в эпике хранить список id подзадач.
public class Subtask extends Task {

    private Long epicIdNumber; //Параметр в рамках какого эпика она выполняется

    public Subtask(String сaseName, String taskDescription, TaskStatus statusTask) {
        super(сaseName, taskDescription, statusTask);
        this.epicIdNumber = epicIdNumber;

    }

    public Long getEpicIdNumber() {
        return epicIdNumber;
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "сaseName='" + сaseName + '\'' +
                ", SubtaskDescription='" + taskDescription + '\'' +
                ", SubtaskIdNumber=" + getTaskIdNumber() +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
}
