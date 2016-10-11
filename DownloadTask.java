package org.blackbell.kamzekam;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kurtcha on 8.10.2016.
 */
public class DownloadTask extends AsyncTask<String, Void, String> {

    public static final int BUFFER_SIZE = 1024;

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                contentBuilder.append(line + "\n");
            }
            Log.i("DOWNLOADED", contentBuilder.toString());
            return contentBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
