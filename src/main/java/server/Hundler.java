package server;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import manager.FileBackedTasksManager;
import tasks.Task;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;

import static jdk.internal.util.xml.XMLStreamWriter.DEFAULT_CHARSET;
import static manager.InMemoryTaskManager.taskIdNumber;

public class Hundler implements HttpHandler {
    private FileBackedTasksManager fileBackedTasksManager;
    GsonBuilder gsonBuilder = new GsonBuilder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, JsonDeserializerContext) ->
                                LocalDateTime.parse(json.getAsString())
                ).registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.toString())
                ).create();
        URI requestURI = httpExchange.getRequestURI();
        String query = requestURI.getQuery();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "GET":
                    System.out.println("GET /tasks/task");
                    if(query == null) {
                        for(Object task : fileBackedTasksManager.gettingTask()) {
                            String jsonString = gson.toJson(task);
                            System.out.println("task" + jsonString);
                        }
                    } else {
                        String str = query.split("=")[1];
                        System.out.println(str);
                        long id = Integer.parseInt(str);
                        fileBackedTasksManager.getTask(id);
                        System.out.println("id task " + fileBackedTasksManager.getTask(id));
                    }
                    httpExchange.sendResponseHeaders(200, 0);
                    break;
                case "POST":
                    final String json = new String(httpExchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET);
                    final Task newTask = gson.fromJson(json, Task.class);
                    if(query == null) {
                        fileBackedTasksManager.creatureTask(newTask);
                    } else {
                        String str = query.split("=")[1];
                        System.out.println(str);
                        long id = Integer.parseInt(str);
                    }
                    httpExchange.sendResponseHeaders(201, 0);
                    break;
                case "DELETE":
                    if(query == null) {
                        fileBackedTasksManager.removeT(taskIdNumber);
                    } else {
                        String idStr = query.split("=")[1];
                        System.out.println(idStr);
                        long id = Integer.parseInt(idStr);
                        System.out.println("1" + fileBackedTasksManager.getTask(id));
                        fileBackedTasksManager.removeT(taskIdNumber);
                        System.out.println(" 2" + fileBackedTasksManager.getTask(id));
                    }
                    httpExchange.sendResponseHeaders(200, 0);
                    break;
                default:
                    httpExchange.sendResponseHeaders(405, 0);
            }
        } finally {
            httpExchange.close();
        }
    }


    private void handleTasks(HttpExchange httpExchange) throws IOException {
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, JsonDeserializerContext) ->
                                LocalDateTime.parse(json.getAsString())
                )
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.toString())
                )
                .create();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "GET":
                    System.out.println("GET /tasks");
                    for(Task task : fileBackedTasksManager.getPrioritizedTasks()) {
                        String jsonString = gson.toJson(task);
                        System.out.println(jsonString);
                    }
                    httpExchange.sendResponseHeaders(200, 0);
                    break;
                default:
                    System.out.println("/tasks ждёт GET-запрос, а получил " + httpExchange.getRequestMethod());
                    httpExchange.sendResponseHeaders(405, 0);
            }
        } finally {
            httpExchange.close();
        }
    }

    public void handleSubtask(HttpExchange httpExchange) throws IOException {
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, JsonDeserializerContext) ->
                                LocalDateTime.parse(json.getAsString())
                )
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.toString())
                )
                .create();
        URI requestURI = httpExchange.getRequestURI();
        String query = requestURI.getQuery();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "GET":
                    System.out.println("GET /tasks/subtask");
                    if(query == null) {
                        System.out.println("Вы не указали индекс");
                    } else {
                        String str = query.split("=")[1];
                        System.out.println(str);
                        long id = Integer.parseInt(str);
                        fileBackedTasksManager.getSubtask(id);
                        System.out.println("id task " + fileBackedTasksManager.getSubtask(id));
                    }
                    httpExchange.sendResponseHeaders(200, 0);
                    break;
                default:
                    httpExchange.sendResponseHeaders(405, 0);
            }
        } finally {
            httpExchange.close();
        }
    }

    public void handleEpic(HttpExchange h) throws IOException {
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, JsonDeserializerContext) ->
                                LocalDateTime.parse(json.getAsString())
                )
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.toString())
                )
                .create();
        try {
            switch (h.getRequestMethod()) {
                case "GET":
                    for(Object task : fileBackedTasksManager.gettingTask()) {
                        String jsonString = gson.toJson(task);
                        System.out.println(jsonString);
                    }
                    h.sendResponseHeaders(200, 0);
                    break;
                default:
                    h.sendResponseHeaders(405, 0);
            }
        } finally {
            h.close();
        }

    }

    public void handleHistory(HttpExchange h) throws IOException {
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, JsonDeserializerContext) ->
                                LocalDateTime.parse(json.getAsString())
                )
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.toString())
                )
                .create();
        try {
            switch (h.getRequestMethod()) {
                case "GET":
                    for(Task task : fileBackedTasksManager.history()) {
                        String jsonString = gson.toJson(task);
                        System.out.println(jsonString);
                    }
                    h.sendResponseHeaders(200, 0);
                    break;
                default:
                    h.sendResponseHeaders(405, 0);
            }
        } finally {
            h.close();
        }
    }

}
