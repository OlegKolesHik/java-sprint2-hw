package manager;

import adapters.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http.HttpTaskManager;
import server.KVServer;
import java.time.LocalDateTime;

public class Managers {

    //возвращает объект-менеджер
    public static TaskManager getDefault()  {
        return new HttpTaskManager(KVServer.PORT);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }
}
