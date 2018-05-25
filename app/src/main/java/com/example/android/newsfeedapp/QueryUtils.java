package com.example.android.newsfeedapp;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.newsfeedapp.news.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getName();

    // Suppress default constructor for noninstantiability
    private QueryUtils() {
        throw new AssertionError("No QueryUtils instances for you!");
    } // This class will never be instantiated, so I can suppress the constructor.


    public static List<News> fetchNewsData(String requestURL) {

        URL url = createUrl(requestURL);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        List<News> news = extractNewsFromJson(jsonResponse);

        return news;
    }

    private static URL createUrl(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving news JSON result", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractNewsFromJson(String newsJson) {
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }

        List<News> news = new ArrayList<>();

        try {
            JSONObject rootJsonObject = new JSONObject(newsJson);
            JSONObject responseJsonObject = rootJsonObject.optJSONObject("response");
            JSONArray newsJsonArray = responseJsonObject.optJSONArray("results");
            for (int i = 0; i < newsJsonArray.length(); i++) {
                JSONObject singleNews = newsJsonArray.optJSONObject(i);

                String section = singleNews.getString("sectionName");
                String title = singleNews.getString("webTitle");
                String publicationDate = singleNews.getString("webPublicationDate");
                String webUrl = singleNews.getString("webUrl");

                JSONArray contributorsArray = singleNews.optJSONArray("tags");

                if (contributorsArray.length() > 0) {
                    ArrayList<String> contributors = new ArrayList<>();
                    for (int j = 0; j < contributorsArray.length(); j++) {
                        JSONObject contributor = contributorsArray.optJSONObject(j);

                        String contributorName = contributor.getString("webTitle");
                        contributors.add(contributorName);
                    }
                    News singleNewsObject = new News(section, title, publicationDate, contributors, webUrl);
                    news.add(singleNewsObject);
                } else {
                    News singleNewsObject = new News(section, title, publicationDate, webUrl);
                    news.add(singleNewsObject);
                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the news JSON results", e);
        }
        return news;
    }
}
