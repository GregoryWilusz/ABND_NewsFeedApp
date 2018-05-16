package com.example.android.newsfeedapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements LoaderCallbacks<List<News>>{

    @BindView(R.id.list_view) ListView mNewsListView;
    @BindView(R.id.empty_state_view) TextView mEmptyTextView;
    @BindView(R.id.loading_indicator) View mIndicatorView;
    public static final String REQUEST_URL = "http://content.guardianapis.com/search?section=books&from-date=2018-05-01&order-by=newest&page=1&page-size=15&api-key=test";
    public static final int NEWS_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        mNewsListView.setEmptyView(mEmptyTextView);
        mIndicatorView.setVisibility(View.GONE);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        } else {
            mIndicatorView.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet_connection);
        }


    }

    @Override
    public Loader onCreateLoader(int id, Bundle bundle) {
        return new NewsLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        mIndicatorView.setVisibility(View.GONE);
        mEmptyTextView.setText(R.string.no_news_found);

        for (News eachNews: news) {
            Log.i("TEST", "Data: " + String.valueOf(eachNews));
        }

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
