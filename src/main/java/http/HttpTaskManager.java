package http;

import client.KVTaskClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import manager.FileBackedTasksManager;
import manager.Managers;
import server.KVServer;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HttpTaskManager extends FileBackedTasksManager {

    private KVTaskClient client;
    private Gson gson;
    private KVServer httpServer;

    public HttpTaskManager(int port){
            super(new File("data.csv"));
        try {
            gson = Managers.getGson();
            httpServer = new KVServer(port);
            httpServer.start();
            client = new KVTaskClient(port);
        }catch (IOException exception){
            System.out.println("Сервер работает");
            exception.printStackTrace();

        }
    }

      public KVServer getKvSever() {
         return this.httpServer;
}

    public void save(){
        String tasksJson = gson.toJson(getTaskT());
        client.put("tasks",tasksJson);

        String epicsJson = gson.toJson(getEpicT());
        client.put("epics",epicsJson);

        String subtasksJson = gson.toJson(getSubtaskT());
        client.put("subtasks",subtasksJson);

        List<Long> historyJs = history().stream().map(Task::getTaskIdNumber).collect(Collectors.toList());
        String historyJson = gson.toJson(historyJs);
        client.put("history",historyJson);

    }

    public void load(KVTaskClient kvTaskClient){
        Type tasksType = new TypeToken<ArrayList<Task>>() {}.getType();
        ArrayList<Task> tasks = gson.fromJson(client.load("tasks"), tasksType);
        tasks.forEach(task -> {
            long id = task.getTaskIdNumber();
            this.taskT.put(id, task);
            this.tasksSorted.put(task, task.getStartTime());
            //увеличиваем идентификатор
            if (id> generateId) {
                generateId = id;
            }
        });

        Type subtasksType = new TypeToken<ArrayList<Subtask>>() {}.getType();
        ArrayList<Subtask> subtasks = gson.fromJson(client.load("subtasks"), subtasksType);
        subtasks.forEach(subtask -> {
            long id = subtask.getTaskIdNumber();
            this.subtaskT.put(id, subtask);
            this.tasksSorted.put(subtask, subtask.getStartTime());
            //увеличиваем идентификатор
            if (id> generateId) {
                generateId = id;
            }
        });

        Type epicsType = new TypeToken<ArrayList<Epic>>() {}.getType();
        ArrayList<Epic> epics = gson.fromJson(client.load("epics"), epicsType);
        epics.forEach(epic -> {
            long id = epic.getTaskIdNumber();
            this.epicT.put(id, epic);
            this.tasksSorted.put(epic, epic.getStartTime());
            //увеличиваем идентификатор
            if (id> generateId) {
                generateId = id;
            }
        });

        Type historyType = new TypeToken<ArrayList<Long>>() {}.getType();
        ArrayList<Long> history = gson.fromJson(client.load("history"), historyType);
        for(Long id : history) {
            historyManager.add(this.findTask(id));
        }
    }
}
