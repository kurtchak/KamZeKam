package org.blackbell.kamzekam;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by kurtcha on 9.10.2016.
 */

public class Content {
    public static final String EVENT_POSITION = "position";

    public static List<Event> events;
//    public static Map<Long, Event> eventsMap;

    static {
        try {
            String content = new DownloadTask().execute("http://dev.duxon.sk/kamzekam/public/api/events").get();

            Log.i("EVENTS", content);
            Gson g = new Gson();
            Type listType = new TypeToken<List<Event>>() {}.getType();
            events = (List<Event>) g.fromJson(content, listType);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

    }

}
