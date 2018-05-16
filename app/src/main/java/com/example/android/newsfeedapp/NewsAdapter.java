package com.example.android.newsfeedapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by grzegorzwilusz on 5/16/18.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    @BindView(R.id.section_text_view) TextView sectionTextView;
    @BindView(R.id.title_text_view) TextView titleTextView;
    @BindView(R.id.publication_date_text_view) TextView publicationDateTextView;

    public NewsAdapter(NewsActivity context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        ButterKnife.bind(this, convertView);

        final News currentNews = getItem(position);

        sectionTextView.setText(currentNews.getmSection());
        titleTextView.setText(currentNews.getmTitle());
        publicationDateTextView.setText(currentNews.getmPublicationDate());

        return convertView;
    }
}
