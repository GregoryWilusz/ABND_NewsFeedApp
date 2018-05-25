package com.example.android.newsfeedapp.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.newsfeedapp.QueryUtils;

import java.util.List;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<News> news = QueryUtils.fetchNewsData(mUrl);

        return news;
    }
}
