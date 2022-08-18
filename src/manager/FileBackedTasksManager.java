package manager;

import tasks.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import static tasks.TaskType.*;


public class FileBackedTasksManager extends InMemoryTaskManager {
    static String file = "data.csv";
    static File data;

//Пусть новый менеджер получает файл для автосохранения в своём конструкторе и сохраняет его в поле
    public FileBackedTasksManager(File data) {
        super(historyManager);
        this.data = new File(file);
        /*createFile(data);*/
    }

// Для этого создайте метод static void main(String[] args) в классе FileBackedTasksManager и реализуйте
// небольшой сценарий:

    public static void main(String[] args) {
        Task task = new Task(0L,"Task name", "Task description", TaskStatus.NEW, TaskType.TASK);
        Epic epic = new Epic(0L,"Epic name", "Epic description", TaskStatus.NEW, TaskType.EPIC);
        Subtask subtask = new Subtask(0L, "Subtask name,", "Epic description", TaskStatus.NEW, TaskType.SUBTASK);

        //Заведите несколько разных задач, эпиков и подзадач.
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(data);
        fileBackedTasksManager.creatureTask(task);
        fileBackedTasksManager.creatureEpic(epic);
        fileBackedTasksManager.creatureSub(subtask);

        //Запросите некоторые из них, чтобы заполнилась история просмотра.
        System.out.println(fileBackedTasksManager.getTask(task.getTaskIdNumber()));
        System.out.println(fileBackedTasksManager.getSubtask(subtask.getTaskIdNumber()));
        System.out.println(fileBackedTasksManager.getEpic(epic.getTaskIdNumber()));

        //Создайте новый FileBackedTasksManager менеджер из этого же файла.
        loadFromFile(new File("java-sprint2-hw/resources/data.csv"));

}
//Создайте метод save без параметров — он будет сохранять текущее состояние менеджера в указанный файл.
//Затем нужно продумать логику метода save. Что он должен сохранять? Все задачи, подзадачи, эпики и историю
// просмотра любых задач. Для удобства работы рекомендуем выбрать текстовый формат CSV
// Заголовок файла нужно писать в файл вначале метода сохранения. Нельзя основываться на том, что
// существует файл и в нем есть первая строка.
//Исключения вида IOException нужно отлавливать внутри метода save и кидать собственное непроверяемое
//исключение ManagerSaveException. Благодаря этому можно не менять сигнатуру методов интерфейса менеджера.

    public void save() {
        StringBuilder stringBuilder = new StringBuilder("id,type,name,status,description,epic" + "\n");

        for (Task task : getTaskT().values()) {
            stringBuilder.append(toStringTask(task)+ "\n");
        }

        for (Epic epic : getEpicT().values()) {
            stringBuilder.append(toStringEpic(epic)+ "\n");
        }
        for (Subtask subtask : getSubtaskT().values()) {
            stringBuilder.append(toStringSubtask(subtask)+ "\n");
        }
        if (data.isFile()) {
            try {
                Files.writeString(Paths.get(file), stringBuilder.toString(), StandardCharsets.UTF_8);
                Files.writeString(Paths.get(file), stringBuilder.append("\r" + toStringHistory(this.historyManager)));

            } catch (IOException exp) {
                throw new manager.ManagerSaveException(exp.getMessage());
            }
        }
    }

    public static void createFile(File data) {
        if(!Files.exists(data.toPath())) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Напишите метод сохранения задачи в строку String toString(Task task) или переопределите базовый.
    public String toStringTask(Task task) {
        StringBuilder taskString = new StringBuilder(task.getTaskIdNumber() + "," +
                 TASK + "," +
                 task.getСaseName() + "," +
                 task.getStatusTask() + "," +
                 task.getTaskDescription() + ",");

        return taskString.toString();
    }

    public String toStringSubtask(Subtask subtask) {
        StringBuilder subtaskString = new StringBuilder(subtask.getTaskIdNumber() + "," +
                SUBTASK + ", " +
                subtask.getСaseName() + "," +
                subtask.getStatusTask() + "," +
                subtask.getTaskDescription()  + "," +
                subtask.getTaskIdNumber());
        return subtaskString.toString();
    }

    public String toStringEpic(Epic epic) {
        StringBuilder epicString = new StringBuilder(epic.getTaskIdNumber() + "," +
                EPIC + "," +
                epic.getСaseName() + "," +
                epic.getStatusTask() + "," +
                epic.getTaskDescription() + ",");
        return epicString.toString();
    }


    //Напишите метод создания задачи из строки id,type,name,status,description,epic
    public Task fromStringTask(String value) {
        String[] newTaskString = value.split(", ");
        switch (newTaskString[0]) {
            case "TASK":
                Task task1 = new Task(newTaskString[2], newTaskString[4]);
                task1.setTaskIdNumber(Long.valueOf(newTaskString[1]));
                task1.setTaskType(TASK);
                return task1;
            case "SUBTASK":
                Subtask subtask1 = new Subtask(newTaskString[2], newTaskString[4]);
                subtask1.setTaskIdNumber(Long.valueOf(newTaskString[0]));
                subtask1.setTaskType(SUBTASK);
                return subtask1;
            case "EPIC" :
                Epic epic1 = new Epic(newTaskString[2], newTaskString[4]);
                epic1.setTaskIdNumber(Long.valueOf(newTaskString[0]));
                epic1.setTaskType(EPIC);
                return epic1;
        } return null;
    }

//Напишите статические методы для сохранения и восстановления менеджера истории из CSV.

    public String toStringHistory(HistoryManager historyManager) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Task task : historyManager.getHistory()) {
            stringBuilder.append(task.getTaskIdNumber() + ",");
        }
        return stringBuilder.toString();
    }

    static List<Integer> fromStringHistory(String value) {
        ArrayList<Integer> listFromString = new ArrayList<>();
        String[] stringBuilder = value.split(" ");
        for (String val : stringBuilder) {
            listFromString.add(Integer.parseInt(val));

        }
        return listFromString;

    }

    //Помимо метода сохранения создайте статический метод static FileBackedTasksManager
    // loadFromFile(File file), который будет восстанавливать данные менеджера из файла при запуске программы

    public static FileBackedTasksManager loadFromFile(File data) {

        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(data);
        if (Files.exists(data.toPath())) {
            try {
                String[] fromFileBackedTasksManager = Files.readString(data.toPath(), StandardCharsets.UTF_8).split(" ");

                for (int i = 1; i < fromFileBackedTasksManager.length; i++) {
                    if (!fromFileBackedTasksManager[i].isEmpty()) {
                        Task newTask = fileBackedTasksManager.fromStringTask(fromFileBackedTasksManager[i]);

                        if (TaskType.TASK.equals(TASK)) {
                            fileBackedTasksManager.creatureTask(newTask);
                        }
                        if (TaskType.SUBTASK.equals(SUBTASK)) {
                            fileBackedTasksManager.creatureSub((Subtask) newTask);
                        }
                        if (TaskType.EPIC.equals(EPIC)) {
                            fileBackedTasksManager.creatureEpic((Epic) newTask);
                        }
                    } else if(i + 1 < fromFileBackedTasksManager.length && fromFileBackedTasksManager[i + 1].isEmpty()) {
                        for(Integer restore: fromStringHistory(fromFileBackedTasksManager[i + 1])) {
                            fileBackedTasksManager.gettingIdTask(Long.valueOf(restore));
                            fileBackedTasksManager.gettingIdSub(Long.valueOf(restore));
                            fileBackedTasksManager.gettingIdEpic(Long.valueOf(restore));
                        }
                    }
                }
        } catch (IOException e) {
                e.printStackTrace();
        }
        } return fileBackedTasksManager;
}

    @Override
    public List<Object> gettingTask() {
        List<Object> ListIdTask = super.gettingTask(); //Cписок всех задач
        save();
        return ListIdTask;

    }

    @Override
    public void clearTask() {  //Удаление всех задач Task
        super.clearTask();
        save();
    }

    @Override
    public void clearSub() { //Удаление всех задач Subtask
        super.clearSub();
        save();
    }

    @Override
    public void clearEpic() {  //Удаление всех задач Epic
        super.clearEpic();
        save();
    }

    @Override
    public void removeT(Long taskIdNumber) { //Удаление по идентификатору Task.
        super.removeT(taskIdNumber);
        save();
    }

    @Override
    public void removeSub(Long subtaskIdNumber) { //Удаление по идентификатору Subtask.
        super.removeSub(subtaskIdNumber);
        save();
    }

    @Override
    public void removeEpic(Long epicIdNumber) { //Удаление по идентификатору Epic.
        super.removeEpic(epicIdNumber);
        save();
    }

    @Override
    public Map<Long, Task> creatureTask(Task task) { //Создание Task
        Map<Long, Task> creatureTask = super.creatureTask(task);
        save();
        return creatureTask;
    }

    @Override
    public Map<Long, Subtask> creatureSub(Subtask subtask) { //Создание Subtask
        Map<Long, Subtask> creatureSub = super.creatureSub(subtask);
        save();
        return creatureSub;
    }

    @Override
    public Map<Long, Epic> creatureEpic(Epic epic) { //Создание Epic
        Map<Long, Epic> creatureEpic = super.creatureEpic(epic);
        save();
        return creatureEpic;
    }

    @Override
    public Map<Long, Task> gettingIdTask(Long taskIdNumber) { //Получение по идентификатору Task
        Map<Long, Task> gettingId = super.gettingIdTask(taskIdNumber);
        save();
        return gettingId;
    }

    @Override
    public Map<Long, Subtask> gettingIdSub(Long subtaskIdNumber) { //Получение по идентификатору Subtask
        Map<Long, Subtask> gettingIdSub = super.gettingIdSub(subtaskIdNumber);
        save();
        return gettingIdSub;
    }

    @Override
    public Map<Long, Epic> gettingIdEpic(Long epicIdNumber) { //Получение по идентификатору Epic
        Map<Long, Epic> gettingIdEpic = super.gettingIdEpic(epicIdNumber);
        save();
        return gettingIdEpic;
    }

    @Override
    public Task getTask(Long taskIdNumber) {
        Task task = super.getTask(taskIdNumber);
        save();
        return task;
    }

    @Override
    public Subtask getSubtask(Long subtaskIdNumber) {
        Subtask subtask = super.getSubtask(subtaskIdNumber);
        save();
        return subtask;
    }

    @Override
    public Epic getEpic(Long epicIdNumber) {
        Epic epic = super.getEpic(epicIdNumber);
        save();
        return epic;
    }

    @Override
    public List<Task> history() {
    List<Task> history = super.history();
        save();
        return history;
    }








}
