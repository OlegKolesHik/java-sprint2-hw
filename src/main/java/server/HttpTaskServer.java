package server;

import com.google.gson.*;
import com.sun.net.httpserver.HttpServer;
import manager.Managers;
import manager.TaskManager;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer {

    public static final int PORT = 8080;
    private HttpServer server;
    private Gson gson;
    private TaskManager taskManager;

    public static void main(String[] args) throws IOException {
        new HttpTaskServer();
    }

    public HttpTaskServer() throws IOException {
        taskManager = Managers.getDefault();
        server = HttpServer.create();
        server.bind(new InetSocketAddress(PORT), 0);
        server.createContext("/tasks", new Hundler());
        server.start();

    }

    public HttpTaskServer(TaskManager taskManager) throws IOException {
        this.taskManager = taskManager;
        gson = Managers.getGson();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", new Hundler());
        server.start();
    }

}
