package client;

import adapters.LocalDateTimeAdapter;
import com.google.gson.*;
import exception.ManagerSaveException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class KVTaskClient {
    public final Gson gson;
    private String url;
    private String apiToken;
    private HttpClient client;


    public KVTaskClient(int port) {
        try {
            this.url = "http://localhost/" + port + "/";

        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url + "register/"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        this.apiToken = response.body();
        } catch (IOException | InterruptedException exception) {
            throw new ManagerSaveException("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

    }

    //Метод String load(String key) должен возвращать состояние менеджера задач через запрос GET /load/<ключ>?API_TOKEN=
    public String load(String key) {
        try {
            HttpRequest httpRequestLoad = HttpRequest.newBuilder()
                    .uri(new URI(url + "load/" + key + "?API_TOKEN=" + apiToken))
                    .GET()
                    .build();

        HttpResponse<String> response = client.send(httpRequestLoad, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new ManagerSaveException("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return key;
    }
//Метод void put(String key, String json) должен сохранять состояние менеджера задач через запрос
// POST /save/<ключ>?API_TOKEN=
    public void put(String key, String json) {
        try {
            final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url + "save/" + key + "?API_TOKEN=" + apiToken)).
                    POST(body)
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException exception) {
            throw new ManagerSaveException("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
